package edu.ib.splendor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AI {
    private static boolean lost;
    private static boolean won;
    private static int gameCounter = 0;
    public static boolean aristocrat;
    private static List<int[]> moves;
    private final BoardController boardController;
    private final HashMap<Integer, Move> possibleMoves;
    private final Player player;
    private final Board board;

    public AI(BoardController boardController, Board board) {
        lost = false;
        won = false;
        aristocrat = false;
        moves = new ArrayList<>();
        this.boardController = boardController;
        this.board = board;
        player = board.getPlayers().get(0);
        possibleMoves = new HashMap<>();
        possibleMoves.put(0, new GetGem(new Gem[]{Gem.RED, Gem.BROWN, Gem.WHITE}, boardController));
        possibleMoves.put(1, new GetGem(new Gem[]{Gem.RED, Gem.BROWN, Gem.GREEN}, boardController));
        possibleMoves.put(2, new GetGem(new Gem[]{Gem.RED, Gem.BROWN, Gem.BLUE}, boardController));
        possibleMoves.put(3, new GetGem(new Gem[]{Gem.RED, Gem.WHITE, Gem.GREEN}, boardController));
        possibleMoves.put(4, new GetGem(new Gem[]{Gem.RED, Gem.WHITE, Gem.BLUE}, boardController));
        possibleMoves.put(5, new GetGem(new Gem[]{Gem.RED, Gem.GREEN, Gem.BLUE}, boardController));
        possibleMoves.put(6, new GetGem(new Gem[]{Gem.BROWN, Gem.WHITE, Gem.GREEN}, boardController));
        possibleMoves.put(7, new GetGem(new Gem[]{Gem.BROWN, Gem.WHITE, Gem.BLUE}, boardController));
        possibleMoves.put(8, new GetGem(new Gem[]{Gem.BROWN, Gem.GREEN, Gem.BLUE}, boardController));
        possibleMoves.put(9, new GetGem(new Gem[]{Gem.WHITE, Gem.GREEN, Gem.BLUE}, boardController));
        possibleMoves.put(10, new GetGem(new Gem[]{Gem.WHITE, Gem.WHITE}, boardController));
        possibleMoves.put(11, new GetGem(new Gem[]{Gem.RED, Gem.RED}, boardController));
        possibleMoves.put(12, new GetGem(new Gem[]{Gem.BROWN, Gem.BROWN}, boardController));
        possibleMoves.put(13, new GetGem(new Gem[]{Gem.GREEN, Gem.GREEN}, boardController));
        possibleMoves.put(14, new GetGem(new Gem[]{Gem.BLUE, Gem.BLUE}, boardController));
        possibleMoves.put(15, new BuildBuilding(boardController, Tier.FIRST, 1));
        possibleMoves.put(16, new BuildBuilding(boardController, Tier.FIRST, 2));
        possibleMoves.put(17, new BuildBuilding(boardController, Tier.FIRST, 3));
        possibleMoves.put(18, new BuildBuilding(boardController, Tier.FIRST, 0));
        possibleMoves.put(19, new BuildBuilding(boardController, Tier.SECOND, 1));
        possibleMoves.put(20, new BuildBuilding(boardController, Tier.SECOND, 2));
        possibleMoves.put(21, new BuildBuilding(boardController, Tier.SECOND, 3));
        possibleMoves.put(22, new BuildBuilding(boardController, Tier.SECOND, 0));
        possibleMoves.put(23, new BuildBuilding(boardController, Tier.THIRD, 1));
        possibleMoves.put(24, new BuildBuilding(boardController, Tier.THIRD, 2));
        possibleMoves.put(25, new BuildBuilding(boardController, Tier.THIRD, 3));
        possibleMoves.put(26, new BuildBuilding(boardController, Tier.THIRD, 0));
    }

    public boolean playMove(int move){
        if (!possibleMoves.containsKey(move)) throw new IllegalArgumentException("There is no such move");
        Move toPlay = possibleMoves.get(move);
        if (move<10 && validateResourcesOne(((GetGem) toPlay).getGemList())&& player.getPossession().values().stream().reduce(Integer::sum).stream().toList().get(0) < 8){
            toPlay.play(board, player);
            return true;
        }
        else if (move<15 && move > 9 && validateResourcesTwo(((GetGem) toPlay).getGemList()) && player.getPossession().values().stream().reduce(Integer::sum).stream().toList().get(0) < 9){
            toPlay.play(board, player);
            return true;
        }
        else if (move>14 && boardController.canBuy(((BuildBuilding) toPlay).getTier(), ((BuildBuilding) toPlay).getIndex(), board, player)) {
            toPlay.play(board, player);
//            boardController.endTurn(board, player);
            if (player.getPoints()>= 15) won = true;
            return true;
        }
        return false;
    }

    public void playTurn(){
        Random random = new Random();
        HashMap<Double, Integer> order = new HashMap<>();
        double[] keys = new double[27];
        for (int i=0; i<27;i++){
            double value = random.nextDouble();
            order.put(value, i);
            keys[i] = value;
        }
        Arrays.sort(keys);
        int[] state = new int[184];
        state[1] = player.getPoints();
        state[2] = player.getReserveNumber();
        state[3] = player.getPossession().get(Gem.BLUE);
        state[4] = player.getPossession().get(Gem.BROWN);
        state[5] = player.getPossession().get(Gem.GREEN);
        state[6] = player.getPossession().get(Gem.RED);
        state[7] = player.getPossession().get(Gem.WHITE);
        state[8] = player.getPossession().get(Gem.GOLD);
//        state[9] = player.getPossession().get(Gem.WHITE);
//        state[10] = player.getPossession().get(Gem.GOLD);
        state[11] = player.getProduction().getOrDefault(Gem.BLUE, 0);
        state[12] = player.getProduction().getOrDefault(Gem.BROWN, 0);
        state[13] = player.getProduction().getOrDefault(Gem.GREEN, 0);
        state[14] = player.getProduction().getOrDefault(Gem.RED, 0);
        state[15] = player.getProduction().getOrDefault(Gem.WHITE, 0);
        state[16] = board.getStored(Gem.BLUE);
        state[17] = board.getStored(Gem.BROWN);
        state[18] = board.getStored(Gem.GREEN);
        state[19] = board.getStored(Gem.RED);
        state[20] = board.getStored(Gem.WHITE);
        state[21] = board.getStored(Gem.GOLD);
        for (int i = 0; i < 4; i++) {
            state[22 + 11*i] = board.getTradeRow().getTierFirstVisible()[i].getPoints();
            if (board.getTradeRow().getTierFirstVisible()[i].getProduction() == Gem.BLUE) state[23 + 11*i] = 1;else state[23 + 11*i] = 0;
            if (board.getTradeRow().getTierFirstVisible()[i].getProduction() == Gem.BROWN) state[24 + 11*i] = 1;else state[24 + 11*i] = 0;
            if (board.getTradeRow().getTierFirstVisible()[i].getProduction() == Gem.GREEN) state[25 + 11*i] = 1;else state[25 + 11*i] = 0;
            if (board.getTradeRow().getTierFirstVisible()[i].getProduction() == Gem.RED) state[26 + 11*i] = 1;else state[26 + 11*i] = 0;
            if (board.getTradeRow().getTierFirstVisible()[i].getProduction() == Gem.WHITE) state[27 + 11*i] = 1;else state[27 + 11*i] = 0;
            state[28 + 11*i] = board.getTradeRow().getTierFirstVisible()[i].getCost().get(Gem.BLUE);
            state[29 + 11*i] = board.getTradeRow().getTierFirstVisible()[i].getCost().get(Gem.BROWN);
            state[30 + 11*i] = board.getTradeRow().getTierFirstVisible()[i].getCost().get(Gem.GREEN);
            state[31 + 11*i] = board.getTradeRow().getTierFirstVisible()[i].getCost().get(Gem.RED);
            state[32 + 11*i] = board.getTradeRow().getTierFirstVisible()[i].getCost().get(Gem.WHITE);
        }
        for (int i = 0; i < 4; i++) {
            state[66 + 11*i] = board.getTradeRow().getTierSecondVisible()[i].getPoints();
            if (board.getTradeRow().getTierSecondVisible()[i].getProduction() == Gem.BLUE) state[67 + 11*i] = 1;else state[67 + 11*i] = 0;
            if (board.getTradeRow().getTierSecondVisible()[i].getProduction() == Gem.BROWN) state[68 + 11*i] = 1;else state[68 + 11*i] = 0;
            if (board.getTradeRow().getTierSecondVisible()[i].getProduction() == Gem.GREEN) state[69 + 11*i] = 1;else state[69 + 11*i] = 0;
            if (board.getTradeRow().getTierSecondVisible()[i].getProduction() == Gem.RED) state[70 + 11*i] = 1;else state[70 + 11*i] = 0;
            if (board.getTradeRow().getTierSecondVisible()[i].getProduction() == Gem.WHITE) state[71 + 11*i] = 1;else state[71 + 11*i] = 0;
            state[72 + 11*i] = board.getTradeRow().getTierSecondVisible()[i].getCost().get(Gem.BLUE);
            state[73 + 11*i] = board.getTradeRow().getTierSecondVisible()[i].getCost().get(Gem.BROWN);
            state[74 + 11*i] = board.getTradeRow().getTierSecondVisible()[i].getCost().get(Gem.GREEN);
            state[75 + 11*i] = board.getTradeRow().getTierSecondVisible()[i].getCost().get(Gem.RED);
            state[76 + 11*i] = board.getTradeRow().getTierSecondVisible()[i].getCost().get(Gem.WHITE);
        }
        for (int i = 0; i < 4; i++) {
            state[110 + 11*i] = board.getTradeRow().getTierThirdVisible()[i].getPoints();
            if (board.getTradeRow().getTierThirdVisible()[i].getProduction() == Gem.BLUE) state[111 + 11*i] = 1;else state[111 + 11*i] = 0;
            if (board.getTradeRow().getTierThirdVisible()[i].getProduction() == Gem.BROWN) state[112 + 11*i] = 1;else state[112 + 11*i] = 0;
            if (board.getTradeRow().getTierThirdVisible()[i].getProduction() == Gem.GREEN) state[113 + 11*i] = 1;else state[113 + 11*i] = 0;
            if (board.getTradeRow().getTierThirdVisible()[i].getProduction() == Gem.RED) state[114 + 11*i] = 1;else state[114 + 11*i] = 0;
            if (board.getTradeRow().getTierThirdVisible()[i].getProduction() == Gem.WHITE) state[115 + 11*i] = 1;else state[115 + 11*i] = 0;
            state[116 + 11*i] = board.getTradeRow().getTierThirdVisible()[i].getCost().get(Gem.BLUE);
            state[117 + 11*i] = board.getTradeRow().getTierThirdVisible()[i].getCost().get(Gem.BROWN);
            state[118 + 11*i] = board.getTradeRow().getTierThirdVisible()[i].getCost().get(Gem.GREEN);
            state[119 + 11*i] = board.getTradeRow().getTierThirdVisible()[i].getCost().get(Gem.RED);
            state[120 + 11*i] = board.getTradeRow().getTierThirdVisible()[i].getCost().get(Gem.WHITE);
        }
        for (int i = 0; i < board.getAristocrats().size(); i++){
            state[164+5*i] = board.getAristocrats().get(i).getBlue();
            state[165+5*i] = board.getAristocrats().get(i).getBrown();
            state[166+5*i] = board.getAristocrats().get(i).getGreen();
            state[167+5*i] = board.getAristocrats().get(i).getRed();
            state[168+5*i] = board.getAristocrats().get(i).getWhite();
        }
        for (int i = 26; i > -1; i--){
            if (playMove(order.get(keys[i]))) {
                state[0] = order.get(keys[i]);
                moves.add(state);
                if (aristocrat) moves.add(new int[]{});
                return;
            }
        }
//        throw new IllegalArgumentException("No possible moves");
        lost = true;
    }

    private boolean validateResourcesTwo(Gem[] gemList) {
        return board.getStored(gemList[0])>3;
    }

    private boolean validateResourcesOne(Gem[] gemList) {
        for (Gem gem: gemList){
            if (board.getStored(gem) < 1){
               return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        while (true){
        ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
        TradeRow tradeRow = new TradeRow(cards.get(0),cards.get(1),cards.get(2));
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Skynet"));
        AI ai = new AI(new BoardController(), new Board(tradeRow, players,  7,7,7,7,7,5));
        while (true) {
            ai.playTurn();
            if (lost) {
                break;
            }
            if (won) {
                saveToFile();
                break;
            }
        }
        }
    }

    private static void saveToFile() throws IOException {
        if (moves.size() <= 25) {
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\results\\"+moves.size()+"\\"+gameCounter+".txt");
        FileWriter writer = null;
        gameCounter++;
        try {
            writer = new FileWriter(file);
//            System.out.println(moves.size());
            for (int[] state : moves) {
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
        }}
    }
}
