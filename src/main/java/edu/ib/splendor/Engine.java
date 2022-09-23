package edu.ib.splendor;

import edu.ib.splendor.controller.GameController;
import edu.ib.splendor.database.entities.*;
import edu.ib.splendor.service.BoardManager;
import edu.ib.splendor.service.DeckGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Engine {
    private static ArrayList<Player> players;

    public static void main(String[] args) {
        ArrayList<ArrayList<Card>> cards = DeckGenerator.generateCards();
        TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
        players.add(new Player());
        players.add(new Player());
        Board board = new Board(tradeRow, players, 7, 7, 7, 7, 7, 5);
        EngineNode initialNode = new EngineNode(board, null, new ArrayList<>(), new ArrayList<>());
        for (Move move: getAllPossibleMoves(board)){
            board = new Board(tradeRow, players, 7, 7, 7, 7, 7, 5);
            move.play(board, board.getPlayers().get(0));
            ArrayList<Player> players = board.getPlayers();
            players.add(players.remove(0));
            ArrayList<Move> moves = new ArrayList<>(initialNode.getMoves());
            moves.add(move);
            initialNode.getChildren().add(new EngineNode(board, initialNode, new ArrayList<>(), moves));
        }


    }

    private static ArrayList<Move> getAllPossibleMoves(Board board) {
        Player currentPlayer = board.getPlayers().get(0);
        ArrayList<Move> moves = new ArrayList<>();
        for (Tier tier : Tier.values()) {
            if (!tier.equals(Tier.RESERVE)) {
                List<Card> cards = board.getTradeRow().getCardsVisible().get(tier);
                for (int i = 0; i < cards.size(); i++) {
                    Card card = cards.get(i);
                    if (canCardBePurchased(currentPlayer, card)) {
                        moves.add(new BuildBuilding(tier, i));
                    }
                }
            }
        }
        GetGem[] possibleGemCombinations = new GetGem[]{
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
                moves.add(getGem);
        }
        if (currentPlayer.getPossession().values().stream().reduce(0, Integer::sum) <= 8) {
            for (Gem gem : Gem.values()) {
                if (!gem.equals(Gem.GOLD)) {
                    if (board.getStored(gem) >= 4) {
                        moves.add(new GetGem(new Gem[]{gem, gem}));
                    }
                }
            }
        }
        if (currentPlayer.getReserve().size() <= 3) {
            for (Tier tier : Tier.values()) {
                if (!tier.equals(Tier.RESERVE)) {
                    for (int i = -1; i < 4; i++) {
                        moves.add(new ReserveBuilding(tier, i));
                    }
                }
            }
        }
        return moves;
    }

    private static boolean canCardBePurchased(Player currentPlayer, Card card) {
        if (card == null) {
            return false;
        }
        HashMap<Gem, Integer> cost = new HashMap<>();
        int goldNeeded = 0;
        goldNeeded = GameController.getGoldNeeded(card, cost, goldNeeded, currentPlayer);
        return goldNeeded <= currentPlayer.getPossession().get(Gem.GOLD);
    }

}
