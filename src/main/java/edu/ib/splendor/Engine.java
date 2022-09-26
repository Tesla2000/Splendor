package edu.ib.splendor;

import edu.ib.splendor.controller.GameController;
import edu.ib.splendor.database.entities.*;
import edu.ib.splendor.service.CommunicationManager;
import edu.ib.splendor.service.DeckGenerator;
import edu.ib.splendor.service.exceptions.GameLostException;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Engine {
    public static HashMap<Integer, Move> moveHashMap = new HashMap<>();
    public static HashMap<Move, Integer> integerHashMap = new HashMap<>();
    private static final double[] pointsCoefficient = new double[2];
    private static final double[] productionCoefficient = new double[2];
    private static final double[] storeCoefficient = new double[2];
    private static final double[] goldCoefficient = new double[2];
    private static final double[] reserveCoefficient = new double[2];
    private static final double[] aristocratDistanceFirstCoefficient = new double[2];
    private static final double[] aristocratDistanceSecondCoefficient = new double[2];
    private static final double[] cartDistanceFirstCoefficient = new double[2];
    private static final double[] cartDistanceSecondCoefficient = new double[2];
    private static final double[] cartPointBias = new double[2];

    static GetGem[] possibleGemCombinations = new GetGem[]{
            new GetGem(new Gem[]{Gem.RED, Gem.GREEN, Gem.BROWN}),
            new GetGem(new Gem[]{Gem.RED, Gem.GREEN, Gem.BLUE}),
            new GetGem(new Gem[]{Gem.RED, Gem.GREEN, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.RED, Gem.BROWN, Gem.BLUE}),
            new GetGem(new Gem[]{Gem.RED, Gem.BROWN, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.RED, Gem.BLUE, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.GREEN, Gem.BROWN, Gem.BLUE}),
            new GetGem(new Gem[]{Gem.GREEN, Gem.BROWN, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.GREEN, Gem.BLUE, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.BROWN, Gem.BLUE, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.BLUE, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.BROWN, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.BROWN, Gem.BLUE}),
            new GetGem(new Gem[]{Gem.GREEN, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.GREEN, Gem.BLUE}),
            new GetGem(new Gem[]{Gem.GREEN, Gem.BROWN}),
            new GetGem(new Gem[]{Gem.RED, Gem.WHITE}),
            new GetGem(new Gem[]{Gem.RED, Gem.BLUE}),
            new GetGem(new Gem[]{Gem.RED, Gem.BROWN}),
            new GetGem(new Gem[]{Gem.RED, Gem.GREEN}),
            new GetGem(new Gem[]{Gem.RED}),
            new GetGem(new Gem[]{Gem.GREEN}),
            new GetGem(new Gem[]{Gem.BROWN}),
            new GetGem(new Gem[]{Gem.WHITE}),
            new GetGem(new Gem[]{Gem.BLUE}),
    };

    static {
        int index = 0;
        for (Tier tier : Tier.values()) {
            if (!tier.equals(Tier.RESERVE)) {
                for (int i = 0; i < 4; i++) {
                    moveHashMap.put(index, new BuildBuilding(tier, i));
                    integerHashMap.put(new BuildBuilding(tier, i), index);
                    index++;
                }
            }
        }
        for (GetGem getGem : possibleGemCombinations) {
            moveHashMap.put(index, getGem);
            integerHashMap.put(getGem, index);
            index++;
        }
        for (Gem gem : Gem.values()) {
            if (!gem.equals(Gem.GOLD)) {
                moveHashMap.put(index, new GetGem(new Gem[]{gem, gem}));
                integerHashMap.put(new GetGem(new Gem[]{gem, gem}), index);
                index++;
            }
        }
        for (Tier tier : Tier.values()) {
            if (!tier.equals(Tier.RESERVE)) {
                for (int i = -1; i < 4; i++) {
                    moveHashMap.put(index, new ReserveBuilding(tier, i));
                    integerHashMap.put(new ReserveBuilding(tier, i), index);
                    index++;
                }
            }
        }

    }

    public static void setCoefficients(int startIndex) throws IOException {
        for (int index = startIndex; index < startIndex + 2; index++) {
            File file = new File("ArtificialIntelligence/coefficients/" + startIndex + ".txt");
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String st;
            ArrayList<String> coefficient = new ArrayList<>();
            while ((st = bufferedReader.readLine()) != null) {
                coefficient.add(st);
            }
            pointsCoefficient[index - startIndex] = Math.abs(Double.parseDouble(coefficient.get(0).split(",")[0]));
            productionCoefficient[index - startIndex] = Math.abs(Double.parseDouble(coefficient.get(1).split(",")[0]));
            storeCoefficient[index - startIndex] = Double.parseDouble(coefficient.get(2).split(",")[0]);
            goldCoefficient[index - startIndex] = Math.abs(Double.parseDouble(coefficient.get(3).split(",")[0]));
            reserveCoefficient[index - startIndex] = -Math.abs(Double.parseDouble(coefficient.get(4).split(",")[0]));
            aristocratDistanceFirstCoefficient[index - startIndex] = Math.abs(Double.parseDouble(coefficient.get(5).split(",")[0]));
            aristocratDistanceSecondCoefficient[index - startIndex] = Math.abs(Double.parseDouble(coefficient.get(6).split(",")[0]));
            cartDistanceFirstCoefficient[index - startIndex] = Math.abs(Double.parseDouble(coefficient.get(7).split(",")[0]));
            cartDistanceSecondCoefficient[index - startIndex] = Math.abs(Double.parseDouble(coefficient.get(8).split(",")[0]));
            cartPointBias[index - startIndex] = Double.parseDouble(coefficient.get(9).split(",")[0]);
        }
    }

    public static Move getBestMove(Board newBoard, int depth, int index) throws GameLostException {
        HashMap<Integer, Double> moveValues = new HashMap<>();
        EngineNode engineNodeAtStart = new EngineNode(newBoard, null, null);
        Integer bestMove = 0;
        double bestScore = -Double.MAX_VALUE;
        ArrayList<Integer> allPossible = getAllPossibleMoves(newBoard);
        if (allPossible.size() == 0) {
            throw new GameLostException();
        }
        for (Integer move : allPossible) {
            Board board = newBoard.deepcopy();
            moveHashMap.get(move).play(board, board.getPlayers().get(0));
            EngineNode initialNode = new EngineNode(board, engineNodeAtStart, move);
            initialNode.getBoard().getPlayers().add(initialNode.getBoard().getPlayers().remove(0));
            moveValues.put(move, alphaBeta(initialNode, depth - 1, -Double.MAX_VALUE, Double.MAX_VALUE, false, index));
            if (moveValues.get(move) >= bestScore) {
                bestMove = move;
                bestScore = moveValues.get(move);
            }
        }
        return moveHashMap.get(bestMove);
    }

    public static void main(String[] args) throws IOException {
        int depth = 3;
        double[] results = new double[10];
        while (true) {
            CommunicationManager.waitForJavaTurn();
            for (int pair = 0; pair < 5; pair++) {
                ArrayList<ArrayList<Cart>> cards = DeckGenerator.generateCards();
                TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
                setCoefficients(2 * pair);
                Board board = new Board(tradeRow, new ArrayList<>(Arrays.asList(new Player(), new Player())), 7, 7, 7, 7, 7, 5);
                int index;
                int moveCounter = 0;
                boolean lost;
                while (true) {
                    lost = false;
                    index = 0;
                    try {
                        getBestMove(board, depth, index).play(board, board.getPlayers().get(0));
                    } catch (GameLostException e) {
                        System.out.println("Game lost");
                        index = 1;
                        lost = true;
                        break;
                    }
                    moveCounter++;
                    if (board.getPlayers().get(0).getPoints() >= 15) {
                        break;
                    }
                    board.getPlayers().add(board.getPlayers().remove(0));
                    index = 1;
                    try {
                        getBestMove(board, depth, index).play(board, board.getPlayers().get(0));
                    } catch (GameLostException e) {
                        System.out.println("Game lost");
                        index = 0;
                        lost = true;
                        break;
                    }
                    moveCounter++;
                    if (board.getPlayers().get(0).getPoints() >= 15) {
                        break;
                    }
                    board.getPlayers().add(board.getPlayers().remove(0));
                }
                if (!lost) {
                    results[2 * pair + index] = 1 + 1.0 / moveCounter;
                    System.out.println(index + " won");
                } else
                    results[2 * pair + index] = 0.5;
            }
            CommunicationManager.respondToPython(results);
            CommunicationManager.passToPython();
        }
    }

    private static void addChildren(EngineNode node) {
        ArrayList<EngineNode> children = new ArrayList<>();
        for (Integer possibleMove : getAllPossibleMoves(node.getBoard())) {
            children.add(new EngineNode(null, node, possibleMove));
        }
        node.setChildren(children);
    }


    private static ArrayList<Integer> getAllPossibleMoves(Board board) {
        Player currentPlayer = board.getPlayers().get(0);
        ArrayList<Integer> moves = new ArrayList<>();
        for (Tier tier : Tier.values()) {
            if (!tier.equals(Tier.RESERVE)) {
                List<Cart> carts = board.getTradeRow().getCardsVisible().get(tier);
                for (int i = 0; i < carts.size(); i++) {
                    Cart cart = carts.get(i);
                    if (canCardBePurchased(currentPlayer, cart)) {
                        moves.add(integerHashMap.get(new BuildBuilding(tier, i)));
                    }
                }
            }
        }
        for (GetGem getGem : possibleGemCombinations) {
            boolean stop = false;
            for (Gem gem : getGem.getGemList()) {
                int playersResource = currentPlayer.getPossession().values().stream().reduce(0, Integer::sum);
                if (board.getStored(gem) == 0 || 10 - playersResource < getGem.getGemList().length) {
                    stop = true;
                    break;
                }
            }
            if (!stop)
                moves.add(integerHashMap.get(getGem));
        }
        if (currentPlayer.getPossession().values().stream().reduce(0, Integer::sum) <= 8) {
            for (Gem gem : Gem.values()) {
                if (!gem.equals(Gem.GOLD)) {
                    if (board.getStored(gem) >= 4) {
                        moves.add(integerHashMap.get(new GetGem(new Gem[]{gem, gem})));
                    }
                }
            }
        }
        if (currentPlayer.getReserve().size() <= 3) {
            for (Tier tier : Tier.values()) {
                if (!tier.equals(Tier.RESERVE)) {
                    for (int i = -1; i < 4; i++) {
                        if (board.getTradeRow().getCardsHidden().get(tier).size() > 0)
                            moves.add(integerHashMap.get(new ReserveBuilding(tier, i)));
                    }
                }
            }
        }
        return moves;
    }

    private static boolean canCardBePurchased(Player currentPlayer, Cart cart) {
        return purchase(currentPlayer, cart);
    }

    public static boolean purchase(Player currentPlayer, Cart cart) {
        if (cart == null) {
            return false;
        }
        HashMap<Gem, Integer> cost = new HashMap<>();
        int goldNeeded = 0;
        goldNeeded = GameController.getGoldNeeded(cart, cost, goldNeeded, currentPlayer);
        return goldNeeded <= currentPlayer.getPossession().get(Gem.GOLD);
    }

    private static double alphaBeta(EngineNode node, int depth, double alpha, double beta, boolean maximizingPlayer, int index) {
        double value;
        if (depth == 0 || node.isTerminal()) {
            return evaluatePosition(node, index);
        }
        if (maximizingPlayer) {
            value = -Double.MAX_VALUE;
            if (node.getBoard() == null)
                forwardBoard(node, moveHashMap.get(node.getLastMove()));
            addChildren(node);
            if (node.getChildren().size() == 0) {
                node.setTerminal(true);
            }
            for (EngineNode engineNode : node.getChildren()) {
                value = Math.max(value, alphaBeta(engineNode, depth - 1, alpha, beta, false, index));
                cleanTree(engineNode);
                if (value >= beta) {
                    break;
                }
                alpha = Math.max(alpha, value);
            }
        } else {
            value = Double.MAX_VALUE;
            if (node.getBoard() == null)
                forwardBoard(node, moveHashMap.get(node.getLastMove()));
            addChildren(node);
            if (node.getChildren().size() == 0) {
                node.setTerminal(true);
            }
            for (EngineNode engineNode : node.getChildren()) {
                value = Math.min(value, alphaBeta(engineNode, depth - 1, alpha, beta, true, index));
                cleanTree(engineNode);
                if (value <= alpha) {
                    break;
                }
                beta = Math.min(beta, value);
            }
        }
        return value;
    }

    private static void forwardBoard(EngineNode node, Move move) {
        if (node.getParent().getBoard() == null)
            forwardBoard(node.getParent(), moveHashMap.get(node.getParent().getLastMove()));
        Board newBoard = node.getParent().getBoard().deepcopy();
        move.play(newBoard, newBoard.getPlayers().get(0));
        newBoard.getPlayers().add(newBoard.getPlayers().remove(0));
        node.setBoard(newBoard);
    }

    private static double evaluatePosition(EngineNode node, int index) {
        if (node.getBoard() == null) {
            forwardBoard(node, moveHashMap.get(node.getLastMove()));
        }
        if (node.getBoard().getPlayers().get(0).getPoints() >= 15) {
            node.setTerminal(true);
            return Double.MAX_VALUE;
        }
        return evalPlayer(node.getBoard().getPlayers().get(0), node.getBoard(), index) - evalPlayer(node.getBoard().getPlayers().get(1), node.getBoard(), index);
    }

    private static double evalPlayer(Player player, Board board, int index) {
        double value = 0;
        for (Aristocrat aristocrat : board.getAristocrats()) {
            double totalCost = aristocrat.getCost().values().stream().reduce(Integer::sum).orElseThrow();
            int totalLack = 0;
            for (Gem gem : Gem.values()) {
                if (!gem.equals(Gem.GOLD)) {
                    totalLack += Math.max(aristocrat.getCost().get(gem) - player.getProduction().getOrDefault(gem, 0), 0);
                }
            }
            value += aristocratDistanceFirstCoefficient[index] * (1 - totalLack / totalCost);
            value += aristocratDistanceSecondCoefficient[index] * (1 - totalLack / totalCost) * (1 - totalLack / totalCost);
        }
        for (Tier tier : Tier.values()) {
            if (!tier.equals(Tier.RESERVE)) {
                for (Cart cart : board.getTradeRow().getCardsVisible().get(tier)) {
                    double totalCost = cart.getCost().values().stream().reduce(Integer::sum).orElseThrow();
                    int totalLack = 0;
                    for (Gem gem : Gem.values()) {
                        if (!gem.equals(Gem.GOLD)) {
                            totalLack += Math.max(cart.getCost().get(gem) - player.getProduction().getOrDefault(gem, 0)
                                    - player.getPossession().get(gem), 0);

                        }
                    }
                    value += cartDistanceFirstCoefficient[index] * (1 - totalLack / totalCost) * (cart.getPoints() + cartPointBias[index]);
                    value += cartDistanceSecondCoefficient[index] * (1 - totalLack / totalCost) * (1 - totalLack / totalCost) * (cart.getPoints() + cartPointBias[index]);
                }
            }
        }
        return value
                + pointsCoefficient[index] * player.getPoints()
                + productionCoefficient[index] * player.getDeck().size()
                + storeCoefficient[index] * player.getPossession().values().stream().reduce(Integer::sum).orElseThrow() * player.getPossession().values().stream().reduce(Integer::sum).orElseThrow()
                + goldCoefficient[index] * player.getPossession().get(Gem.GOLD)
                + reserveCoefficient[index] * player.getReserve().size();
    }

    private static void cleanTree(EngineNode startEngineNode) {
        ArrayList<EngineNode> toPurge = new ArrayList<>();
        for (EngineNode childNode : startEngineNode.getChildren()) {
            if (childNode.getChildren().size() == 0) {
                toPurge.add(childNode);
            } else {
                cleanTree(childNode);
            }
        }
        for (EngineNode purged : toPurge) {
            startEngineNode.getChildren().remove(purged);
        }
    }
}
