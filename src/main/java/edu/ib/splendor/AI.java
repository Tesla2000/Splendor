package edu.ib.splendor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AI {
    private static boolean lost;
    private static boolean won;
    private static int gameCounter = 2000;
    //    public static boolean aristocrat;
//    private static List<ArrayList<Integer>> moves;
    private static List<int[]> moves;
    private final BoardController boardController;
    private final HashMap<Integer, Move> possibleMoves;
    private final Player player;
    private final Board board;

    public AI(BoardController boardController, Board board) {
        lost = false;
        won = false;
//        aristocrat = false;
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

    public boolean playMove(int move) {
        if (!possibleMoves.containsKey(move)) throw new IllegalArgumentException("There is no such move");
        Move toPlay = possibleMoves.get(move);
        if (move < 10 && validateResourcesOne(((GetGem) toPlay).getGemList()) && player.getPossession().values().stream().reduce(Integer::sum).stream().toList().get(0) < 8) {
            toPlay.play(board, player);
            return true;
        } else if (move < 15 && move > 9 && validateResourcesTwo(((GetGem) toPlay).getGemList()) && player.getPossession().values().stream().reduce(Integer::sum).stream().toList().get(0) < 9) {
            toPlay.play(board, player);
            return true;
        } else if (move > 14 && boardController.canBuy(((BuildBuilding) toPlay).getTier(), ((BuildBuilding) toPlay).getIndex(), board, player)) {
            toPlay.play(board, player);
//            boardController.endTurn(board, player);
            if (player.getPoints() >= 15) won = true;
            return true;
        }
        return false;
    }

    public void playTurn(boolean manual) {
        Random random = new Random();
        HashMap<Double, Integer> order = new HashMap<>();
        double[] keys = new double[27];
        if (!manual) {
            for (int i = 0; i < 27; i++) {
                double value = random.nextDouble();
                order.put(value, i);
                keys[i] = value;
            }
            Arrays.sort(keys);
        }
        int[] state = new int[172];
        state[1] = player.getPoints();
        state[2] = player.getReserve().size();
        state[3] = player.getPossession().get(Gem.BLUE);
        state[4] = player.getPossession().get(Gem.BROWN);
        state[5] = player.getPossession().get(Gem.GREEN);
        state[6] = player.getPossession().get(Gem.RED);
        state[7] = player.getPossession().get(Gem.WHITE);
        state[8] = player.getPossession().get(Gem.GOLD);
        state[9] = player.getProduction().getOrDefault(Gem.BLUE, 0);
        state[10] = player.getProduction().getOrDefault(Gem.BROWN, 0);
        state[11] = player.getProduction().getOrDefault(Gem.GREEN, 0);
        state[12] = player.getProduction().getOrDefault(Gem.RED, 0);
        state[13] = player.getProduction().getOrDefault(Gem.WHITE, 0);
        state[14] = board.getStored(Gem.BLUE);
        state[15] = board.getStored(Gem.BROWN);
        state[16] = board.getStored(Gem.GREEN);
        state[17] = board.getStored(Gem.RED);
        state[18] = board.getStored(Gem.WHITE);
        state[19] = board.getStored(Gem.GOLD);
        int t = 0;
        for (Tier tier : Tier.values())
            if (!tier.equals(Tier.RESERVE)) {
                for (int i = 0; i < 4; i++) {
                    state[20 + 11 * i + 44 * t] = board.getTradeRow().getCard(tier, i).getPoints();
                    if (board.getTradeRow().getCard(tier, i).getProduction() == Gem.BLUE)
                        state[21 + 11 * i + 44 * t] = 1;
                    else state[21 + 11 * i + 44 * t] = 0;
                    if (board.getTradeRow().getCard(tier, i).getProduction() == Gem.BROWN)
                        state[22 + 11 * i + 44 * t] = 1;
                    else state[22 + 11 * i + 44 * t] = 0;
                    if (board.getTradeRow().getCard(tier, i).getProduction() == Gem.GREEN)
                        state[23 + 11 * i + 44 * t] = 1;
                    else state[23 + 11 * i + 44 * t] = 0;
                    if (board.getTradeRow().getCard(tier, i).getProduction() == Gem.RED) state[24 + 11 * i] = 1;
                    else state[24 + 11 * i + 44 * t] = 0;
                    if (board.getTradeRow().getCard(tier, i).getProduction() == Gem.WHITE)
                        state[25 + 11 * i + 44 * t] = 1;
                    else state[25 + 11 * i + 44 * t] = 0;
                    state[26 + 11 * i + 44 * t] = board.getTradeRow().getCard(tier, i).getCost().get(Gem.BLUE);
                    state[27 + 11 * i + 44 * t] = board.getTradeRow().getCard(tier, i).getCost().get(Gem.BROWN);
                    state[28 + 11 * i + 44 * t] = board.getTradeRow().getCard(tier, i).getCost().get(Gem.GREEN);
                    state[29 + 11 * i + 44 * t] = board.getTradeRow().getCard(tier, i).getCost().get(Gem.RED);
                    state[30 + 11 * i + 44 * t] = board.getTradeRow().getCard(tier, i).getCost().get(Gem.WHITE);
                }
                t++;
            }
        for (int i = 0; i < board.getAristocrats().size(); i++) {
            state[152 + 5 * i] = board.getAristocrats().get(i).getBlue();
            state[153 + 5 * i] = board.getAristocrats().get(i).getBrown();
            state[154 + 5 * i] = board.getAristocrats().get(i).getGreen();
            state[155 + 5 * i] = board.getAristocrats().get(i).getRed();
            state[156 + 5 * i] = board.getAristocrats().get(i).getWhite();
        }
        for (int i = 26; i > -1; i--) {
            if (!manual) {
                if (playMove(order.get(keys[i]))) {
                    state[0] = order.get(keys[i]);
                    moves.add(state);
                    return;
                }
            } else {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Play the next move: ");
                int move = scanner.nextInt();
                playMove(move);
                scanner.close();
                state[0] = move;
                moves.add(state);
                System.out.print("[");
                for (int s : state) {
                    System.out.print(s + ", ");
                }
                System.out.print("]");
                return;
            }
        }
//        throw new IllegalArgumentException("No possible moves");
        lost = true;
    }

    private boolean validateResourcesTwo(Gem[] gemList) {
        return board.getStored(gemList[0]) > 3;
    }

    private boolean validateResourcesOne(Gem[] gemList) {
        for (Gem gem : gemList) {
            if (board.getStored(gem) < 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        while (gameCounter < 10000) {
            ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
            TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Skynet"));
            AI ai = new AI(new BoardController(), new Board(tradeRow, players, 7, 7, 7, 7, 7, 5));
            while (true) {
                ai.playTurn(false);
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

    private Integer[] convertStateToMachineInput(Integer[] state) {
        List<Integer> MachineInput = Arrays.asList(state);
        for (int j = 0; j < 12; j++) {
            for (int i = 0; i < 5; i++) {
                int cost = state[26 + i + 11 * j];
                int production = state[9 + i];
                MachineInput.set(26 + i + 11 * j, Math.max(0, cost - production));
                MachineInput.add(cost - production - state[3 + i]);
            }
        }
        for (int i = 0; i < 12; i++) {
            for (int k = 0; k < 5; k++) {
                if (MachineInput.get(21 + k + 11 * i) != 0) {
                    MachineInput.set(21 + k + 11 * i, 0);
                    for (int j = 0; j < 12; j++) {
                        if (MachineInput.get(26 + 11 * j + k) != 0)
                            MachineInput.set(21 + k + 11 * i, MachineInput.get(26 + 11 * j + k) + 1);
                    }
                }
            }
        }
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 5; j++) {
                if (MachineInput.get(21 + j + 11 * i) != 0) {
                    int sum = 0;
                    for (int k = 0; k < 4; k++) {
                        if (MachineInput.get(150 + j + 5 * k) - MachineInput.get(9 + j) > 0)
                            sum++;
                    }
                    MachineInput.add(sum);
                    break;
                }
                MachineInput.add(0);
            }
        }
        int move = MachineInput.get(0);
        MachineInput.remove(0);
        for (int i = 0; i < 27; i++)
            MachineInput.add(0, 0);
        MachineInput.set(move, 1);
        return MachineInput.toArray(new Integer[0]);
    }


    private static void saveToFile() throws IOException {
        if (moves.size() <= 25) {
            File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\results\\" + moves.size() + "\\" + gameCounter + ".txt");
            FileWriter writer = null;
            gameCounter++;
            try {
                writer = new FileWriter(file);
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
            }
        }
    }
}
