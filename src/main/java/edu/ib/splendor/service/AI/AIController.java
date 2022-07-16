package edu.ib.splendor.service.AI;

import edu.ib.splendor.database.entities.*;
import edu.ib.splendor.service.BoardController;
import edu.ib.splendor.service.CommunicationController;
import edu.ib.splendor.service.GameLostException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AIController {
    public static ArrayList<Node> readNodesFromFile(String path) throws IOException {
        String st;
        ArrayList<Node> nodes = new ArrayList<>();
        File file = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((st = reader.readLine()) != null) {
            ArrayList<Double> list = new ArrayList<>(Arrays.stream(st.split(",")).map(Double::parseDouble).toList());
            double bias = list.remove(list.size() - 1);
            nodes.add(new Node(list, bias));
        }
        return nodes;
    }

    public static ArrayList<MoveValuePair> convertNodesToOutput(ArrayList<Node> nodes, ArrayList<Integer> input) {
        ArrayList<MoveValuePair> output = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            output.add(new MoveValuePair(multiply(nodes.get(i), input), i));
        }
        return output;
    }

    public static double multiply(Node node, ArrayList<Integer> input) {
        double result = 0;
        for (int i = 0; i < node.coefficients().size(); i++) {
            result += node.coefficients().get(i) * input.get(i);
        }
        result += node.bias();
        return result;
    }

    public static void saveAsMaster(String id, int masterCounter) throws IOException {
        String st;
        StringBuilder builder = new StringBuilder();
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\coefficients\\" + id + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((st = reader.readLine()) != null) {
            builder.append(st).append("\n");
        }
        reader.close();
        file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + masterCounter + ".txt");
        FileWriter writer = new FileWriter(file);
        writer.write(builder.toString());
        writer.close();
    }

    public static void saveAsMaster(int id, int masterCounter) throws IOException {
        saveAsMaster(String.valueOf(id), masterCounter);
    }

    public static ArrayList<Integer> getState(Board board, ArrayList<Player> players){
        ArrayList<Integer> state = new ArrayList<>();
        for (Gem gem : Gem.values()) state.add(board.getStored(gem));
        for (Player p : players) {
            state.add(p.getPoints());
            state.add(p.getReserve().size());
            for (Gem gem : Gem.values()) {
                state.add(p.getPossession().get(gem));
                if (!gem.equals(Gem.GOLD)) {
                    state.add(p.getProduction().getOrDefault(gem, 0));
                }
            }
            for (Tier tier : Tier.values())
                if (!tier.equals(Tier.RESERVE)) {
                    for (int i = 0; i < 4; i++) {
                        if (board.getTradeRow().getCard(tier, i) != null) {
                            state.add(board.getTradeRow().getCard(tier, i).getPoints());
                            for (Gem gem : Gem.values()) {
                                if (!gem.equals(Gem.GOLD)) {
                                    if (board.getTradeRow().getCard(tier, i).getProduction() == gem) {
                                        int sum = 0;
                                        for (Tier tier1 : Tier.values()) {
                                            if (!tier1.equals(Tier.RESERVE)) {
                                                sum += Math.min(1, Math.max(0, board.getTradeRow().getCard(tier, i).getCost().getOrDefault(gem, 0) - p.getProduction().getOrDefault(gem, 0)));
                                            }
                                        }
                                        state.add(sum);
                                    } else state.add(0);
                                    state.add(Math.max(0, board.getTradeRow().getCard(tier, i).getCost().getOrDefault(gem, 0) - p.getProduction().getOrDefault(gem, 0)));
                                    state.add(board.getTradeRow().getCard(tier, i).getCost().getOrDefault(gem, 0) - p.getProduction().getOrDefault(gem, 0) - p.getPossession().getOrDefault(gem, 0));
                                }
                            }
                        } else {
                            for (int j = 0; j < 16; j++)
                                state.add(0);
                        }
                    }
                }
            
            for (int i = 0; i < board.getAristocrats().size(); i++) {
                for (Gem gem: Gem.values())
                    if (!gem.equals(Gem.GOLD))
                        state.add(board.getAristocrats().get(i).getCost().get(gem));
            }
            for (int i = board.getAristocrats().size(); i < 4; i++) {
                for (i=0; i<5; i++)
                    state.add(0);
            }
        }
        return state;
    }

    public static void playMoveAI(ArrayList<Node> nodes, Board board, ArrayList<Player> players, HashMap<Integer, Move> possibleMoves) throws GameLostException {
        ArrayList<MoveValuePair> order = convertNodesToOutput(nodes, getState(board, players));
        playMove(order, players.get(0), board, possibleMoves);
    }

    public static void playTurn(ArrayList<Player> players, ArrayList<Node> nodes, Board board, HashMap<Integer, Move> possibleMoves) throws GameLostException {
        Player player = players.get(0);
        playMoveAI(nodes, board, players, possibleMoves);
        BoardController.endTurn(board, player);
    }

    private static void playMove(Player player, List<Integer> sequence, Board board, HashMap<Integer, Move> possibleMoves) throws GameLostException {
        int playersResource = player.getPossession().values().stream().reduce(0, Integer::sum);
        for (int index : sequence) {
            if (!(possibleMoves.get(index) instanceof ReserveBuilding)) break;
            if (BoardController.can_card_be_reserved(player, board, ((ReserveBuilding) possibleMoves.get(index)).getTier(), ((ReserveBuilding) possibleMoves.get(index)).getIndex())) {
                BoardController.reserveCard(player, board, ((ReserveBuilding) possibleMoves.get(index)).getTier(), ((ReserveBuilding) possibleMoves.get(index)).getIndex());
                return;
            }
        }
        sequence = sequence.stream().filter(elem -> possibleMoves.get(elem) instanceof BuildBuilding).toList();
        if (playersResource == 10) {
            for (Integer option : sequence) {
                if (BoardController.canBuy(((BuildBuilding) possibleMoves.get(option)).getTier(), ((BuildBuilding) possibleMoves.get(option)).getIndex(), board, player)) {
                    BoardController.buyEstate(((BuildBuilding) possibleMoves.get(option)).getTier(), ((BuildBuilding) possibleMoves.get(option)).getIndex(), board, player);
                    return;
                }
            }
            throw new GameLostException();
        }
        if (BoardController.canBuy(((BuildBuilding) possibleMoves.get(sequence.get(0))).getTier(), ((BuildBuilding) possibleMoves.get(sequence.get(0))).getIndex(), board, player)) {
            BoardController.buyEstate(((BuildBuilding) possibleMoves.get(sequence.get(0))).getTier(), ((BuildBuilding) possibleMoves.get(sequence.get(0))).getIndex(), board, player);
        } else {
            List<Gem> gotten = new ArrayList<>();
            int i = 0;
            int canBeTaken = 3;
            while (player.getPossession().values().stream().reduce(0, Integer::sum) < 10 && gotten.size() < 3 && canBeTaken > 0) {
                if (i >= sequence.size()) {
                    break;
                }
                ArrayList<GemAmountPair> lack = BoardController.lackingGems(((BuildBuilding) possibleMoves.get(sequence.get(i))).getTier(), ((BuildBuilding) possibleMoves.get(sequence.get(i))).getIndex(), board, player);
                if (lack == null || !canBeTaken(lack, player.getPossession(), board)) {
                    i++;
                    continue;
                }
                lack = (ArrayList<GemAmountPair>) lack.stream().filter(l -> l.integer != 0).collect(Collectors.toList());
                lack.sort((e1, e2) -> e2.integer - e1.integer);
                if (lack.size() == 0) {
                    if (gotten.size() == 0 && BoardController.getCard(((BuildBuilding) possibleMoves.get(sequence.get(i))).getTier(), ((BuildBuilding) possibleMoves.get(sequence.get(i))).getIndex(), board, player) != null) {
                        BoardController.buyEstate(((BuildBuilding) possibleMoves.get(sequence.get(i))).getTier(), ((BuildBuilding) possibleMoves.get(sequence.get(i))).getIndex(), board, player);
                        return;
                    } else {
                        i++;
                        continue;
                    }
                }
                if (lack.size() == 1 && player.getPossession().values().stream().reduce(0, Integer::sum) <= 8 && board.getStored(lack.get(0).gem) >= 4 && canBeTaken == 3 && !gotten.contains(lack.get(0).gem)) {
                    BoardController.collectGem(lack.get(0).gem, board, player);
                    BoardController.collectGem(lack.get(0).gem, board, player);
                    break;
                } else {
                    for (GemAmountPair gemAmountPair : lack) {
                        if (!gotten.contains(gemAmountPair.gem) && board.getStored(gemAmountPair.gem) > 0 && gotten.size() < 3 && canBeTaken > 0) {
                            BoardController.collectGem(gemAmountPair.gem, board, player);
                            gotten.add(gemAmountPair.gem);
                            if (i != 0) canBeTaken--;
                        }
                    }
                    lack = BoardController.lackingGems(((BuildBuilding) possibleMoves.get(sequence.get(0))).getTier(), ((BuildBuilding) possibleMoves.get(sequence.get(0))).getIndex(), board, player);
                    if (lack == null) {
                        i++;
                        continue;
                    }
                    canBeTaken = 10 - playersResource - lack.stream().map(e -> e.integer).reduce(0, Integer::sum);
                    if (canBeTaken == 0 && gotten.size() != 0)
                        return;
                    i++;
                }
            }
        }
    }

    private static void playMove(ArrayList<MoveValuePair> order, Player player, Board board, HashMap<Integer, Move> possibleMoves) throws GameLostException {
        List<Integer> sequence = order.stream().sorted((e1, e2) -> Double.compare(e2.value(), e1.value())).map(MoveValuePair::move).toList();
        playMove(player, sequence, board, possibleMoves);
    }

    private static boolean canBeTaken(ArrayList<GemAmountPair> lack, HashMap<Gem, Integer> possession,Board board) {
        if (lack.stream().map(e -> e.integer).reduce(0, Integer::sum) > 10 - possession.values().stream().reduce(0, Integer::sum))
            return false;
        for (GemAmountPair gemAmountPair : lack) {
            if (board.getStored(gemAmountPair.gem) < gemAmountPair.integer) return false;
        }
        return true;
    }

    private ArrayList<Integer> convertToSequence(HashMap<Double, Integer> order) {
        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<Double> values = new ArrayList<>(order.keySet());
        values.sort((double1, double2) -> -double1.compareTo(double2));
        for (Double value : values) {
            results.add(order.get(value));
        }
        return results;
    }

    public static void trainAI(AI ai, int betterIndicator) throws IOException {
        while (true) {
            ai.initializeTraining();
            for (int id = 0; id < ai.getAllPlayers().size(); id++) {
                for (int i = 0; i < 50; i++) {
                    ai.setOrder(ai.getAllPlayers(), ai.getMasterCounter(), ai.getCurrentPlayers(), id);
                    ai.playGame(id);
                }
            }
            ai.setBestScore(0);
            for (int i = 0; i < 100; i++) {
                ai.setOrder(ai.getAllPlayers(), ai.getMasterCounter(), ai.getCurrentPlayers(), ai.getBest());
                ai.playGame(ai.getBest());
            }
            if (ai.getBestScore() > betterIndicator) {
                System.out.println("New master " + (ai.getMasterCounter() + 1) + ": " + (int) Math.round(ai.getBestScore()) + "/" + 100);
                ai.setMasterCounter(ai.getMasterCounter() + 1);
                AIController.saveAsMaster(ai.getBest(), ai.getMasterCounter());
            }
            System.out.println("Best pretender: " + (int) Math.round(ai.getBestScore()) + "/" + 100);
            CommunicationController.respondToPython(ai.getScores());
            CommunicationController.passToPython();
        }
    }
}
