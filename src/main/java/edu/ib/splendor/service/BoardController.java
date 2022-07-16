package edu.ib.splendor.service;

import edu.ib.splendor.database.entities.*;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardController {

    public static Card getCard(Tier tier, int index, Board board, Player currentPlayer){
        Card card = null;
        if (!tier.equals(Tier.RESERVE)) card = board.getTradeRow().getCard(tier, index);
        else if (currentPlayer.getReserve().size() > index) card = currentPlayer.getReserve().get(index);
        return card;
    }

    public static void buyEstate(Tier tier, int index, Board board, Player currentPlayer) {
        Card card = getCard(tier, index, board, currentPlayer);
        HashMap<Gem, Integer> cost = new HashMap<>();
        int goldNeeded = 0;
        goldNeeded = GameController.getGoldNeeded(card, cost, goldNeeded, currentPlayer);
        if (goldNeeded <= currentPlayer.getPossession().get(Gem.GOLD)) {
            GameController.pay(card, cost, goldNeeded, currentPlayer, board);
            if (!tier.equals(Tier.RESERVE))
                currentPlayer.addCard(board.getTradeRow().takeCard(card.getTier(), index));
            else {
                currentPlayer.addCard(card);
                currentPlayer.removeReserve(card);
            }
            endTurn(board, currentPlayer);
        }
    }

    public static boolean canBuy(Tier tier, int index, Board board, Player currentPlayer) {
        Card card = getCard(tier, index, board, currentPlayer);
        if (card == null) {
            return false;
        }
        HashMap<Gem, Integer> cost = new HashMap<>();
        int goldNeeded = 0;
        goldNeeded = GameController.getGoldNeeded(card, cost, goldNeeded, currentPlayer);
        return goldNeeded <= currentPlayer.getPossession().get(Gem.GOLD);
        }

    public static ArrayList<GemAmountPair> lackingGems(Tier tier, int index, Board board, Player currentPlayer) {
        Card card =null;
        if (!tier.equals(Tier.RESERVE)) card = board.getTradeRow().getCard(tier, index);
        else if (currentPlayer.getReserve().size()>index) card = currentPlayer.getReserve().get(index);
        if (card == null) return null;
        ArrayList<GemAmountPair> cost = new ArrayList<>();
        int goldNeeded = 0;
        for (Gem gem: card.getCost().keySet()) {
            cost.add(new GemAmountPair((Math.max(card.getCost().getOrDefault(gem,0) - currentPlayer.getPossession().getOrDefault(gem,0)
                                - currentPlayer.getProduction().getOrDefault(gem,0), 0)), gem));
            goldNeeded += Math.max(card.getCost().getOrDefault(gem,0) - currentPlayer.getPossession().getOrDefault(gem,0)
                    - currentPlayer.getProduction().getOrDefault(gem,0), 0);
        }
        if (goldNeeded <= currentPlayer.getPossession().get(Gem.GOLD))
        return new ArrayList<>();
        return cost;
        }

    public static void collectGem(Gem gem, Board board, Player currentPlayer){
        currentPlayer.changeGem(gem, -1);
        board.changeStored(gem, -1);
//        endTurn(board, currentPlayer);
    }

    public static void endTurn(Board board, Player currentPlayer) {
        getAristocrats(board, currentPlayer);
    }


    public static void getAristocrats(Board board, Player currentPlayer) {
        ArrayList<Aristocrat> toRemove = new ArrayList<>();
//        AI.aristocrat = false;
        for (Aristocrat aristocrat: board.getAristocrats())
            if (
                    aristocrat.getCost().get(Gem.BLUE)<=currentPlayer.getProduction().getOrDefault(Gem.BLUE,0) &&
                    aristocrat.getCost().get(Gem.RED)<=currentPlayer.getProduction().getOrDefault(Gem.RED,0) &&
                    aristocrat.getCost().get(Gem.GREEN)<=currentPlayer.getProduction().getOrDefault(Gem.GREEN,0) &&
                    aristocrat.getCost().get(Gem.BROWN)<=currentPlayer.getProduction().getOrDefault(Gem.BROWN,0) &&
                    aristocrat.getCost().get(Gem.WHITE)<=currentPlayer.getProduction().getOrDefault(Gem.WHITE,0)
            ) {
                toRemove.add(aristocrat);
                currentPlayer.addAristocrat(aristocrat);
            }
        for (Aristocrat aristocrat: toRemove){
            board.removeAristocrat(aristocrat);
        }
    }

    public static boolean can_card_be_reserved(Player player, Board board, Tier tier, int index){
        return player.getReserve().size() < 3 && board.getTradeRow().getCard(tier, index) != null;
    }

    public static void reserveCard(Player player, Board board, Tier tier, int index){
        player.addReserve(board.getTradeRow().takeCard(tier, index));
        if (player.getPossession().values().stream().reduce(Integer::sum).stream().toList().get(0) < 10){
            player.addGem(Gem.GOLD);
            board.changeStored(Gem.GOLD, -1);
        }
    }
}
