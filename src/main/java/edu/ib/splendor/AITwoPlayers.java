package edu.ib.splendor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AITwoPlayers {
    private static boolean lost;
    private static boolean won;
    private static List<ArrayList<Integer>> moves;
    private static int gameCounter = 0;
    private final BoardController boardController;
    private final HashMap<Integer, BuildBuilding> possibleMoves;
    private final ArrayList<Player> players;
    private final Board board;
    private int moveFirst = 0;
    private int moveSecond = 0;

    public AITwoPlayers(BoardController boardController, ArrayList<Player> players, Board board) {
        this.boardController = boardController;
        this.players = players;
        this.board = board;
        moves = new ArrayList<>();
        possibleMoves = new HashMap<>();
        possibleMoves.put(0, new BuildBuilding(boardController, Tier.FIRST, 1));
        possibleMoves.put(1, new BuildBuilding(boardController, Tier.FIRST, 2));
        possibleMoves.put(2, new BuildBuilding(boardController, Tier.FIRST, 3));
        possibleMoves.put(3, new BuildBuilding(boardController, Tier.FIRST, 0));
        possibleMoves.put(4, new BuildBuilding(boardController, Tier.SECOND, 1));
        possibleMoves.put(5, new BuildBuilding(boardController, Tier.SECOND, 2));
        possibleMoves.put(6, new BuildBuilding(boardController, Tier.SECOND, 3));
        possibleMoves.put(7, new BuildBuilding(boardController, Tier.SECOND, 0));
        possibleMoves.put(8, new BuildBuilding(boardController, Tier.THIRD, 1));
        possibleMoves.put(9, new BuildBuilding(boardController, Tier.THIRD, 2));
        possibleMoves.put(10, new BuildBuilding(boardController, Tier.THIRD, 3));
        possibleMoves.put(11, new BuildBuilding(boardController, Tier.THIRD, 0));
    }

    public static void main(String[] args) throws IOException {
        int wonCounter = 0;
        int lostCounter = 0;
        while (gameCounter < 10000) {
            won = false;
            lost = false;
            ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
            TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Skynet"));
            players.add(new Player("DeepBlue"));
            AITwoPlayers ai = new AITwoPlayers(new BoardController(), players, new Board(tradeRow, players, 7, 7, 7, 7, 7, 5));
            while (true) {
                ai.playTurn();
                if (lost) {
                    if (lostCounter % 1000 == 0) System.out.println("Lost: " + lostCounter);
                    lostCounter++;
                    break;
                }
                if (won) {
                    saveToFile();
                    if (wonCounter % 1000 == 0) System.out.println("Won: " + wonCounter);
                    wonCounter++;
                    break;
                }
            }
        }
    }

    public void playTurn() {
        Random random = new Random();
        Player player = players.get(0);
        ArrayList<Integer> state = new ArrayList<>();
        state.add(board.getStored(Gem.BLUE));
        state.add(board.getStored(Gem.BROWN));
        state.add(board.getStored(Gem.GREEN));
        state.add(board.getStored(Gem.RED));
        state.add(board.getStored(Gem.WHITE));
        state.add(board.getStored(Gem.GOLD));
        HashMap<Double, Integer> order = new HashMap<>();
        double[] keys = new double[12];
        for (int i = 0; i < keys.length; i++) {
            double value = random.nextDouble();
            order.put(value, i);
            keys[i] = value;
        }
        Arrays.sort(keys);
        state.add(player.getPoints());
        state.add(player.getReserveNumber());
        state.add(player.getPossession().get(Gem.BLUE));
        state.add(player.getPossession().get(Gem.BROWN));
        state.add(player.getPossession().get(Gem.GREEN));
        state.add(player.getPossession().get(Gem.RED));
        state.add(player.getPossession().get(Gem.WHITE));
        state.add(player.getPossession().get(Gem.GOLD));
        state.add(player.getProduction().getOrDefault(Gem.BLUE, 0));
        state.add(player.getProduction().getOrDefault(Gem.BROWN, 0));
        state.add(player.getProduction().getOrDefault(Gem.GREEN, 0));
        state.add(player.getProduction().getOrDefault(Gem.RED, 0));
        state.add(player.getProduction().getOrDefault(Gem.WHITE, 0));
        for (int i = 0; i < 4; i++) {
            if (board.getTradeRow().getTierFirstVisible()[i]!= null) {
                state.add(board.getTradeRow().getTierFirstVisible()[i].getPoints());
                for (Gem gem : Gem.values()) {
                    if (!gem.equals(Gem.GOLD)) {
                        if (board.getTradeRow().getTierFirstVisible()[i].getProduction() == gem) state.add(1);
                        else state.add(0);
                        state.add(Math.max(0, board.getTradeRow().getTierFirstVisible()[i].getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0)));
                        state.add(board.getTradeRow().getTierFirstVisible()[i].getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0) - player.getPossession().getOrDefault(gem, 0));
                    }
                }
            } else {
                for (int j=0; j<11; j++)
                    state.add(0);
            }
        }
        for (int i = 0; i < 4; i++) {
            state.add(board.getTradeRow().getTierSecondVisible()[i].getPoints());
            for (Gem gem : Gem.values()) {
                if (!gem.equals(Gem.GOLD)) {
                    if (board.getTradeRow().getTierSecondVisible()[i].getProduction() == gem) state.add(1);
                    else state.add(0);
                    state.add(Math.max(0, board.getTradeRow().getTierSecondVisible()[i].getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0)));
                    state.add(board.getTradeRow().getTierSecondVisible()[i].getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0) - player.getPossession().getOrDefault(gem, 0));
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            state.add(board.getTradeRow().getTierThirdVisible()[i].getPoints());
            for (Gem gem : Gem.values()) {
                if (!gem.equals(Gem.GOLD)) {
                    if (board.getTradeRow().getTierThirdVisible()[i].getProduction() == gem) state.add(1);
                    else state.add(0);
                    state.add(Math.max(0, board.getTradeRow().getTierThirdVisible()[i].getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0)));
                    state.add(board.getTradeRow().getTierThirdVisible()[i].getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0) - player.getPossession().getOrDefault(gem, 0));
                }
            }
        }
        for (int i = 0; i < board.getAristocrats().size(); i++) {
            state.add(board.getAristocrats().get(i).getBlue());
            state.add(board.getAristocrats().get(i).getBrown());
            state.add(board.getAristocrats().get(i).getGreen());
            state.add(board.getAristocrats().get(i).getRed());
            state.add(board.getAristocrats().get(i).getWhite());
        }
        for (double key : keys)
            state.add(order.get(key));
        try {
            playMove(order, player);
            if (player.getPoints() >= 15) {
                won = true;
            }
            boardController.endTurn(board, player);
            players.add(players.remove(0));
        } catch (IllegalArgumentException e) {
            lost = true;
        }
        state.add(0, moveFirst);
        state.add(1, moveSecond);
//        if (player.getName().equals("DeepBlue")){
//            boolean b = true;
//        }
        moves.add(state);
    }

    private boolean playMove(HashMap<Double, Integer> order, Player player) {
        moveFirst = 0;
        moveSecond = 0;
        ArrayList<Integer> sequence = convertToSequence(order);
        int playersResource = player.getPossession().values().stream().reduce(0, Integer::sum);
        if (playersResource == 10) {
            for (Integer option : sequence) {
                if (boardController.canBuy(possibleMoves.get(option).getTier(), possibleMoves.get(option).getIndex(), board, player)) {
                    boardController.buyEstate(possibleMoves.get(option).getTier(), possibleMoves.get(option).getIndex(), board, player);
                    return true;
                }
            }
            throw new IllegalArgumentException();
        }
        if (boardController.canBuy(possibleMoves.get(sequence.get(0)).getTier(), possibleMoves.get(sequence.get(0)).getIndex(), board, player)) {
            boardController.buyEstate(possibleMoves.get(sequence.get(0)).getTier(), possibleMoves.get(sequence.get(0)).getIndex(), board, player);
            return true;
        } else {
            List<Gem> gotten = new ArrayList<>();
            int i = 0;
            while (player.getPossession().values().stream().reduce(0, Integer::sum) < 10 && gotten.size() < 3) {
                if (i >= order.size()) {
                    break;
                }
                ArrayList<GemAmountPair> lack = boardController.lackingGems(possibleMoves.get(sequence.get(i)).getTier(), possibleMoves.get(sequence.get(i)).getIndex(), board, player);
                if (lack==null || lack.stream().map(e -> e.integer).reduce(0, Integer::sum) > 10 - playersResource) {
                    i++;
                    continue;
                }
                lack.sort((e1, e2) -> e2.integer - e1.integer);
                if (lack.size() == 0) {
                    i++;
                    continue;
                }
                if (moveFirst == 0) moveFirst = i;
                else if (moveSecond == 0) moveSecond = i;
                if (lack.size() == 1 && player.getPossession().values().stream().reduce(0, Integer::sum) <= 8 && board.getStored(lack.get(0).gem) >= 0) {
                    boardController.collectGem(lack.get(0).gem, board, player);
                    boardController.collectGem(lack.get(0).gem, board, player);
                    break;
                } else {
                    for (GemAmountPair gemAmountPair : lack) {
                        if (!gotten.contains(gemAmountPair.gem) && board.getStored(gemAmountPair.gem) > 0) {
                            boardController.collectGem(gemAmountPair.gem, board, player);
                            gotten.add(gemAmountPair.gem);
                            break;
                        } else {
                            lack = boardController.lackingGems(possibleMoves.get(sequence.get(i)).getTier(), possibleMoves.get(sequence.get(i)).getIndex(), board, player);
                            if (lack.stream().map(e -> e.integer).reduce(0, Integer::sum) == 10 - playersResource)
                                return false;
                            i++;
                            break;
                        }
                    }
                }
            }
        }
        return false;
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

    private static void saveToFile() throws IOException {
//        if (moves.size()/2 <= 25) {
//            System.out.println(moves.size());
            File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\results\\two\\" + moves.size()/2 + "\\" + gameCounter + ".txt");
            FileWriter writer = null;
            gameCounter++;
            try {
                writer = new FileWriter(file);
                for (ArrayList<Integer> state : moves) {
                    for (int element : state) {
                        writer.write(String.valueOf(element));
                        writer.write(",");
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    writer.close();
                }
            }
//        }
    }


}
