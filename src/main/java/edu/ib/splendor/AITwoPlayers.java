package edu.ib.splendor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AITwoPlayers {
    private static boolean lost;
    private static boolean won;
    private static List<ArrayList<Integer>> moves;
    private static int gameCounter = 0;
    private final BoardController boardController;
    private final HashMap<Integer, Move> possibleMoves;
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
                possibleMoves.put(counter, new ReserveBuilding(boardController, tier, i));
                counter++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int wonCounter = 0;
        int lostCounter = 0;
        int blueCounter = 0;
        int skyCounter = 0;
        Random random = new Random();
        while (gameCounter<1500) {
            won = false;
            lost = false;
            ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
            TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
            ArrayList<Player> players = new ArrayList<>();
            if (random.nextBoolean()) {
                players.add(new Player("Skynet"));
                players.add(new Player("DeepBlue"));
            } else {
                players.add(new Player("DeepBlue"));
                players.add(new Player("Skynet"));
            }
            AITwoPlayers ai = new AITwoPlayers(new BoardController(), players, new Board(tradeRow, players, 7, 7, 7, 7, 7, 5));
            while (true) {
//                ai.playTurn(players.get(0).getName().equals("Skynet"));
//                ai.playTurn(false);
                ai.playTurn(true);
                if (lost) {
                    if (lostCounter % 100 == 0) System.out.println("Lost: " + lostCounter);
                    lostCounter++;
                    break;
                }
                if (won) {
//                    saveToFile();
                    if (players.get(1).getName().equals("DeepBlue")) {
//                        saveToFile();
//                        if (blueCounter % 100 == 0) System.out.println("Blue won: " + blueCounter);
                        blueCounter++;
                    } else {
//                        saveToFile();
//                        if (skyCounter % 100 == 0) System.out.println("Sky won: " + skyCounter);
                        skyCounter++;
                    }
                    wonCounter++;
                    if (wonCounter % 100 == 0) {
                        System.out.println("Finished: " + wonCounter);
                        System.out.println("Sky/Blue = " + skyCounter + "/" + blueCounter);
                    }
                    break;
                }
            }
        }
    }

    public void playTurn(boolean AIGenerated) throws IOException {
        Random random = new Random();
        Player player = players.get(0);
        ArrayList<Integer> state = new ArrayList<>();
        for (Gem gem: Gem.values()) state.add(board.getStored(gem));
        HashMap<Double, Integer> order = new HashMap<>();
//        double[] keys = new double[12];
//        Arrays.sort(keys);
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
                                    if (board.getTradeRow().getCard(tier, i).getProduction() == gem)
                                        state.add(1);
                                    else state.add(0);
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
//        for (double key : keys)
//            state.add(order.get(key));
        if (!AIGenerated) {
            for (int i = 0; i < 12; i++) {
                double value = random.nextDouble();
                order.put(value, i);
            }
        } else {
            if (player.getName().equals("Skynet")) order = generateOrder("1", state);
            else order = generateOrder("2", state);
        }
        try {
//            if (player.getName().equals("Skynet")){ //lookup
//                int points = players.stream().filter(player1 -> player1.getName().equals("DeepBlue")).toList().get(0).getPoints();
//                boolean b = true;
//            }
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
        moves.add(state);
    }

    private HashMap<Double, Integer> generateOrder(String number, ArrayList<Integer> state) throws IOException {
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\results\\two\\coefitents\\" + number);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String st;
        HashMap<Double, Integer> result = new HashMap<>();
        int counter = 0;
        while ((st = bufferedReader.readLine()) != null){
            String[] coefficients = st.split(",");
            double value = 0;
            for (int i=0;i<state.size(); i++){
                value += Double.parseDouble(coefficients[i])*state.get(i);
            }
            value += Double.parseDouble(coefficients[state.size()]);
            result.put(value, counter);
            counter++;
        }
        return result;
    }

    private boolean playMove(HashMap<Double, Integer> order, Player player) {
        moveFirst = -1;
        moveSecond = -1;
        ArrayList<Integer> sequence = convertToSequence(order);
        int playersResource = player.getPossession().values().stream().reduce(0, Integer::sum);
        for (int index: sequence){
            if (!(possibleMoves.get(index) instanceof ReserveBuilding)) break;
            if (boardController.can_card_be_reserved(player, board, ((ReserveBuilding) possibleMoves.get(index)).getTier(), ((ReserveBuilding) possibleMoves.get(index)).getIndex())){
                boardController.reserveCard(player, board, ((ReserveBuilding) possibleMoves.get(index)).getTier(), ((ReserveBuilding) possibleMoves.get(index)).getIndex());
                return true;
            }
        }
        sequence = (ArrayList<Integer>) sequence.stream().filter(elem->elem>=15).toList();
        if (playersResource == 10) {
            for (Integer option : sequence) {
                if (boardController.canBuy(((BuildBuilding)possibleMoves.get(option)).getTier(), ((BuildBuilding)possibleMoves.get(option)).getIndex(), board, player)) {
                    boardController.buyEstate(((BuildBuilding)possibleMoves.get(option)).getTier(), ((BuildBuilding)possibleMoves.get(option)).getIndex(), board, player);
                    moveFirst = option;
                    return true;
                }
            }
            throw new IllegalArgumentException();
        }
        if (boardController.canBuy(((BuildBuilding)possibleMoves.get(0)).getTier(), ((BuildBuilding)possibleMoves.get(0)).getIndex(), board, player)) {
            boardController.buyEstate(((BuildBuilding)possibleMoves.get(0)).getTier(), ((BuildBuilding)possibleMoves.get(0)).getIndex(), board, player);
            moveFirst = sequence.get(0);
            return true;
        } else {
            List<Gem> gotten = new ArrayList<>();
            int i = 0;
            int canBeTaken = 3;
            while (player.getPossession().values().stream().reduce(0, Integer::sum) < 10 && gotten.size() < 3 && canBeTaken > 0) {
                if (i >= order.size()) {
                    break;
                }
                ArrayList<GemAmountPair> lack = boardController.lackingGems(((BuildBuilding)possibleMoves.get(i)).getTier(), ((BuildBuilding)possibleMoves.get(i)).getIndex(), board, player);
                if (lack==null || !canBeTaken(lack, player.getPossession())) {
                    i++;
                    continue;
                }
                lack = (ArrayList<GemAmountPair>) lack.stream().filter(l->l.integer!=0).collect(Collectors.toList());
                lack.sort((e1, e2) -> e2.integer - e1.integer);
                if (lack.size() == 0) {
                    if (gotten.size() == 0){
                        boardController.buyEstate(((BuildBuilding)possibleMoves.get(i)).getTier(), ((BuildBuilding)possibleMoves.get(i)).getIndex(), board, player);
                        return true;
                    }else {
                        i++;
                        continue;
                    }
                }
                if (moveFirst == -1) moveFirst = sequence.get(i);
                else if (moveSecond == -1) moveSecond = sequence.get(i);
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
                    lack = boardController.lackingGems(((BuildBuilding)possibleMoves.get(0)).getTier(), ((BuildBuilding)possibleMoves.get(0)).getIndex(), board, player);
                    canBeTaken = 10 - playersResource - lack.stream().map(e -> e.integer).reduce(0, Integer::sum);
                    if (canBeTaken == 0 && gotten.size() != 0)
                        return false;
                    i++;
                }
            }
        }
        return false;
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

    private static void saveToFile() {
//        if (moves.size()/2 <= 25) {
//            File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\results\\one\\"+moves.size()/2+"\\" + gameCounter + ".txt");
            File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\results\\two\\2\\" + gameCounter + ".txt");
            gameCounter++;
//            System.out.println(gameCounter);
            try (FileWriter writer = new FileWriter(file)) {
                for (int i = (moves.size() + 1) % 2; i < moves.size(); i += 2) {
                    for (int element : moves.get(i)) {
                        writer.write(String.valueOf(element));
                        writer.write(",");
                    }
                    writer.write("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
//            }
        }
    }


}
