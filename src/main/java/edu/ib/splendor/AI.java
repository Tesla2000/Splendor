package edu.ib.splendor;

import java.util.ArrayList;

public class AI {
    private Board board;
    private Player bot;

    public AI(Board board, Player bot) {
        this.board = board;
        this.bot = bot;
    }

    private double realPurchaseCost(Player player, Card card) {
        double cost = 0;
        for (Gem gem : Gem.values()) {
            cost += Math.max(0, card.getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0));
            cost += Math.max(0, card.getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0)
                    - player.getPossession().getOrDefault(gem, 0));
            cost += Math.max(0, card.getCost().getOrDefault(gem, 0) - player.getProduction().getOrDefault(gem, 0)
                    - player.getPossession().getOrDefault(gem, 0) - player.getPossession().getOrDefault(Gem.GOLD, 0));
        }
        if (cost==0)
            return -1;
        return cost;
    }

    private Integer distanceFromAristocrat(Player player, Aristocrat aristocrat) {
        int distance = 0;
        for (Gem gem : Gem.values())
            distance += Math.max(0, aristocrat.getCost().getOrDefault(gem, 0)
                    - player.getProduction().getOrDefault(gem, 0));
        return distance;
    }


    private double scorePosition(Player player, Board board) {
        double score = 20*player.getPoints();
        for (Card card : board.getTradeRow().getTierFirstVisible())
            score -= realPurchaseCost(player, card);
        for (Card card : board.getTradeRow().getTierSecondVisible())
            score -= realPurchaseCost(player, card);
        for (Card card : board.getTradeRow().getTierThirdVisible())
            score -= realPurchaseCost(player, card);
        for (Aristocrat aristocrat : board.getAristocrats()){
            score -= distanceFromAristocrat(player,aristocrat);
        }
        score -= player.allGems();
        score -= player.getReserveNumber();
        return score;
    }
}
