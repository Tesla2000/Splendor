package edu.ib.splendor.database.entities;

import edu.ib.splendor.service.BoardManager;

public class BuildBuilding implements Move {
    private final Tier tier;
    private final int index;

    public BuildBuilding(Tier tier, int index) {
        this.tier = tier;
        this.index = index;
    }

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
