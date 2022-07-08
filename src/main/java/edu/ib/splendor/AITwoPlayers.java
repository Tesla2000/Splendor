package edu.ib.splendor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AITwoPlayers {
    private static List<ArrayList<Integer>> moves;
    private static boolean won;
    private static int masterCounter = 19;
    private static boolean lost;
    private final BoardController boardController;
    private final HashMap<Integer, Move> possibleMoves;
    private final ArrayList<Player> players;
    private final Board board;

    public AITwoPlayers(BoardController boardController, ArrayList<Player> players, Board board) {
        this.boardController = boardController;
        this.players = players;
        this.board = board;
        moves = new ArrayList<>();
        possibleMoves = new HashMap<>();
        int counter = 0;
        for (Tier tier: Tier.values()){
            int loops;
            if (tier.equals(Tier.RESERVE)) loops = 3;
            else loops = 4;
            for (int i=0;i<loops;i++){
                possibleMoves.put(counter, new BuildBuilding(boardController, tier, i));
                counter++;
            }
            for (int i=0;i<4;i++){
                if (!tier.equals(Tier.RESERVE)) {
                    possibleMoves.put(counter, new ReserveBuilding(boardController, tier, i));
                    counter++;
                }
            }
        }
    }

    private static void waitForMyTurn() throws IOException {
        String st;
        boolean stop = false;
        do {
            File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\communication.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((st = reader.readLine()) != null) {
                if (st.equals("java")) stop = true;
            }
            reader.close();
        } while (!stop);
    }

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

    public static ArrayList<MoveValuePair> convertNodesToOutput(ArrayList<Node> nodes, ArrayList<Integer> input){
        ArrayList<MoveValuePair> output = new ArrayList<>();
        for (int i=0;i<nodes.size();i++){
            output.add(new MoveValuePair(multiply(nodes.get(i), input), i));
        }
        return output;
    }

    private static double multiply(Node node, ArrayList<Integer> input) {
        double result = 0;
        for (int i=0; i< node.coefficients().size(); i++){
            result += node.coefficients().get(i)*input.get(i);
        }
        result += node.bias();
        return result;
    }

    public static void main(String[] args) throws IOException {
//        while (true) {
            Random random = new Random();
            double[] scores = new double[16];
            int best = 0;
            double bestScore = 0;
            waitForMyTurn();
            ArrayList<ArrayList<Node>> allPlayers = new ArrayList<>();
            for (int id = 0; id < 16; id++) {
                allPlayers.add(readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\coefficients\\" + id + ".txt"));
            }
            ArrayList<Node> master = readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + masterCounter + ".txt");
            ArrayList<PlayerWithNodes> currentPlayers;
            for (int id = 0; id < allPlayers.size(); id++) {
                for (int i = 0; i < 50; i++) {
                    lost = false;
                    won = false;
                    currentPlayers = new ArrayList<>();
                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
                    if (random.nextBoolean()) {
                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
                    } else {
                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    }
                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::player).toList());
                    AITwoPlayers ai = new AITwoPlayers(new BoardController(), players, new Board(tradeRow, players, 7, 7, 7, 7, 7, 5));
                    while (true) {
                        ai.playTurn(currentPlayers.get(0).nodes());
                        if (lost) {
                            break;
                        }
                        if (won) {
                            if (currentPlayers.get(0).player().getName().equals("Pretender")) {
                                scores[id] = scores[id] + 1 + 1.0 / moves.size() / 50;
                                if (scores[id] > bestScore) {
                                    best = id;
                                    bestScore = scores[id];
                                }
                            }
                            break;
                        }
                        currentPlayers.add(currentPlayers.remove(0));
                    }
                }
            }
            bestScore = 0;
//            for (int m = 1; m<20; m++) {
//                bestScore = 0;
                for (int i = 0; i < 100; i++) {
                    won = false;
                    lost = false;
                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
                    currentPlayers = new ArrayList<>();
//                    ArrayList<PlayerWithNodes> currentPlayers = new ArrayList<>();
                    if (random.nextBoolean()) {
                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
//                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + m + ".txt")));
                    currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(best)));
                    } else {
                    currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(best)));
//                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + m + ".txt")));
                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    }
                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::player).toList());
                    AITwoPlayers ai = new AITwoPlayers(new BoardController(), players, new Board(tradeRow, players, 7, 7, 7, 7, 7, 5));
                    while (true) {
                        ai.playTurn(currentPlayers.get(0).nodes());
//                    if (players.get(0).getName().equals("Master")){
//                        ArrayList<Card> deck = players.get(0).getDeck();
//                        HashMap<Gem, Integer> resources = players.get(0).getPossession();
//                        int points = players.get(0).getPoints();
//                        points = players.get(0).getPoints();
//                    }
                        if (lost) {
                            break;
                        }
                        if (won) {
                            if (currentPlayers.get(0).player().getName().equals("Pretender")) {
                                bestScore++;
                            }
                            break;
                        }
                        currentPlayers.add(currentPlayers.remove(0));
                    }
                }
            if (bestScore > 60) {
                System.out.println("New master "+(masterCounter+1)+": " + (int) Math.round(bestScore) + "/" + 100);
                saveAsMaster(best);
            }
                System.out.println("Best pretender: " + (int) Math.round(bestScore) + "/" + 100);
//                System.out.println("Best pretender"+m+": " + (int) Math.round(bestScore) + "/" + 1000);
            respondToPython(scores);
            passToPython();
            }
//    }
//    }

    private static void saveAsMaster(String id) throws IOException {
        String st;
        StringBuilder builder = new StringBuilder();
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\coefficients\\" + id + ".txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((st= reader.readLine()) != null){
            builder.append(st).append("\n");
        }
        reader.close();
        masterCounter++;
        file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\"+masterCounter+".txt");
        FileWriter writer = new FileWriter(file);
        writer.write(builder.toString());
        writer.close();
    }

    private static void respondToPython(double[] scores) throws IOException {
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\response.txt");
        FileWriter writer = new FileWriter(file);
        StringBuilder builder = new StringBuilder();
        for (double score: scores) builder.append(score).append(",");
        String st = builder.toString();
        st = st.substring(0, st.length()-1);
        writer.write(st);
        writer.close();
    }

    private static void passToPython() throws IOException {
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\communication.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("python");
        writer.close();
    }

    private static void saveAsMaster(int id) throws IOException {
        saveAsMaster(String.valueOf(id));
    }

    private static double factorial(int floor) {
        double total = 1.0;
        for (int i=1;i<floor;i++){
            total *= i;
        }
        return total;
    }

    public void playTurn(ArrayList<Node> nodes) {
        Player player = players.get(0);
        ArrayList<Integer> state = new ArrayList<>();
        for (Gem gem: Gem.values()) state.add(board.getStored(gem));
        for (Player p: players) {
            state.add(p.getPoints());
            state.add(p.getReserve().size());
            for (Gem gem:Gem.values()) {
                state.add(p.getPossession().get(gem));
                if (!gem.equals(Gem.GOLD)){
                    state.add(p.getProduction().getOrDefault(gem, 0));
                }
            }
            for (Tier tier: Tier.values())
                if (!tier.equals(Tier.RESERVE)) {
                    for (int i = 0; i < 4; i++) {
                        if (board.getTradeRow().getCard(tier, i) != null) {
                            state.add(board.getTradeRow().getCard(tier, i).getPoints());
                            for (Gem gem : Gem.values()) {
                                if (!gem.equals(Gem.GOLD)) {
                                    if (board.getTradeRow().getCard(tier, i).getProduction() == gem) {
                                        int sum = 0;
                                        for (Tier tier1: Tier.values()){
                                            if (!tier1.equals(Tier.RESERVE)) {
                                                sum += Math.min(1, Math.max(0, board.getTradeRow().getCard(tier, i).getCost().getOrDefault(gem, 0) - p.getProduction().getOrDefault(gem, 0)));
                                            }
                                        }
                                        state.add(sum);
                                    }else state.add(0);
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
                state.add(board.getAristocrats().get(i).getBlue());
                state.add(board.getAristocrats().get(i).getBrown());
                state.add(board.getAristocrats().get(i).getGreen());
                state.add(board.getAristocrats().get(i).getRed());
                state.add(board.getAristocrats().get(i).getWhite());
            }
            for (int i = board.getAristocrats().size(); i < 4; i++) {
                state.add(0);
                state.add(0);
                state.add(0);
                state.add(0);
                state.add(0);
            }
        }
        ArrayList<MoveValuePair> order = convertNodesToOutput(nodes, state);
        try {
            playMove(order, player);
        } catch (GameLostException e){
            lost = true;
        }
        if (player.getPoints() >= 15) {
            won = true;
        }
        boardController.endTurn(board, player);
        players.add(players.remove(0));
        moves.add(state);
    }

    private void playMove(Player player, List<Integer> sequence) throws GameLostException {
        int playersResource = player.getPossession().values().stream().reduce(0, Integer::sum);
        for (int index: sequence){
            if (!(possibleMoves.get(index) instanceof ReserveBuilding)) break;
            if (boardController.can_card_be_reserved(player, board, ((ReserveBuilding) possibleMoves.get(index)).getTier(), ((ReserveBuilding) possibleMoves.get(index)).getIndex())){
                boardController.reserveCard(player, board, ((ReserveBuilding) possibleMoves.get(index)).getTier(), ((ReserveBuilding) possibleMoves.get(index)).getIndex());
                return ;
            }
        }
        sequence = sequence.stream().filter(elem->possibleMoves.get(elem) instanceof BuildBuilding).toList();
        if (playersResource == 10) {
            for (Integer option : sequence) {
                if (boardController.canBuy(((BuildBuilding)possibleMoves.get(option)).getTier(), ((BuildBuilding)possibleMoves.get(option)).getIndex(), board, player)) {
                    boardController.buyEstate(((BuildBuilding)possibleMoves.get(option)).getTier(), ((BuildBuilding)possibleMoves.get(option)).getIndex(), board, player);
                    return ;
                }
            }
            throw new GameLostException();
        }
        if (boardController.canBuy(((BuildBuilding)possibleMoves.get(sequence.get(0))).getTier(), ((BuildBuilding)possibleMoves.get(sequence.get(0))).getIndex(), board, player)) {
            boardController.buyEstate(((BuildBuilding)possibleMoves.get(sequence.get(0))).getTier(), ((BuildBuilding)possibleMoves.get(sequence.get(0))).getIndex(), board, player);
        } else {
            List<Gem> gotten = new ArrayList<>();
            int i = 0;
            int canBeTaken = 3;
            while (player.getPossession().values().stream().reduce(0, Integer::sum) < 10 && gotten.size() < 3 && canBeTaken > 0) {
                if (i >= sequence.size()) {
                    break;
                }
                ArrayList<GemAmountPair> lack = boardController.lackingGems(((BuildBuilding)possibleMoves.get(sequence.get(i))).getTier(), ((BuildBuilding)possibleMoves.get(sequence.get(i))).getIndex(), board, player);
                if (lack==null || !canBeTaken(lack, player.getPossession())) {
                    i++;
                    continue;
                }
                lack = (ArrayList<GemAmountPair>) lack.stream().filter(l->l.integer!=0).collect(Collectors.toList());
                lack.sort((e1, e2) -> e2.integer - e1.integer);
                if (lack.size() == 0) {
                    if (gotten.size() == 0 && boardController.getCard(((BuildBuilding)possibleMoves.get(sequence.get(i))).getTier(), ((BuildBuilding)possibleMoves.get(sequence.get(i))).getIndex(), board, player)!= null){
                        boardController.buyEstate(((BuildBuilding)possibleMoves.get(sequence.get(i))).getTier(), ((BuildBuilding)possibleMoves.get(sequence.get(i))).getIndex(), board, player);
                        return ;
                    }else {
                        i++;
                        continue;
                    }
                }
                if (lack.size() == 1 && player.getPossession().values().stream().reduce(0, Integer::sum) <= 8 && board.getStored(lack.get(0).gem) >= 4 && canBeTaken >= 2 && !gotten.contains(lack.get(0).gem)) {
                    boardController.collectGem(lack.get(0).gem, board, player);
                    boardController.collectGem(lack.get(0).gem, board, player);
                    break;
                } else {
                    for (GemAmountPair gemAmountPair : lack) {
                        if (!gotten.contains(gemAmountPair.gem) && board.getStored(gemAmountPair.gem) > 0 && gotten.size()<3 && canBeTaken > 0) {
                            boardController.collectGem(gemAmountPair.gem, board, player);
                            gotten.add(gemAmountPair.gem);
                            if (i != 0) canBeTaken--;
                        }
                    }
                    lack = boardController.lackingGems(((BuildBuilding)possibleMoves.get(sequence.get(0))).getTier(), ((BuildBuilding)possibleMoves.get(sequence.get(0))).getIndex(), board, player);
                    if (lack == null) {
                        i++;
                        continue;
                    }
                    canBeTaken = 10 - playersResource - lack.stream().map(e -> e.integer).reduce(0, Integer::sum);
                    if (canBeTaken == 0 && gotten.size() != 0)
                        return ;
                    i++;
                }
            }
        }
    }

    private void playMove(ArrayList<MoveValuePair> order, Player player) throws GameLostException {
        List<Integer> sequence = order.stream().sorted((e1,e2)-> Double.compare(e2.value(),e1.value())).map(MoveValuePair::move).toList();
        playMove(player, sequence);
    }

    private boolean canBeTaken(ArrayList<GemAmountPair> lack, HashMap<Gem, Integer> possession) {
        if (lack.stream().map(e -> e.integer).reduce(0, Integer::sum) > 10 - possession.values().stream().reduce(0, Integer::sum)) return false;
        for (GemAmountPair gemAmountPair: lack){
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
}
