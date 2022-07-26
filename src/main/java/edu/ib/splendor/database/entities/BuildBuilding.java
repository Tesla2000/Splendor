package edu.ib.splendor.database.entities;

import edu.ib.splendor.service.BoardManager;

public record BuildBuilding(Tier tier,
                            int index) implements Move {

    @Override
    public void play(Board board, Player player) {
        BoardManager.buyEstate(tier, index, board, player);
    }

    public Tier getTier() {
        return tier;
    }

    public int getIndex() {
        return index;
    }
}
