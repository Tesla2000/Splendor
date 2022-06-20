package edu.ib.splendor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AI {
    private static boolean lost;
    private static boolean won;
    private static List<Integer> moves;
    private final BoardController boardController;
    private final HashMap<Integer, Move> possibleMoves;
    private final Player player;
    private final Board board;

    public AI(BoardController boardController, Board board) {
        lost = false;
        won = false;
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
            boardController.endTurn(board, player);
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
        for (int i = 26; i > -1; i--){
            if (playMove(order.get(keys[i]))) {
                moves.add(i);
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
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\buildings.txt");
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(moves.size());
            for (Integer move: moves) {
                writer.write(";");
                writer.write(move);
            }
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
