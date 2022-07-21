package edu.ib.splendor.database.entities;

import edu.ib.splendor.service.BoardController;

public class ReserveBuilding implements Move {
    private final Tier tier;
    private final int index;

    public ReserveBuilding(Tier tier, int index) {
        this.tier = tier;
        this.index = index;
    }

    @Override
    public void play(Board board, Player player) {
        BoardController.reserveCard(player, board, tier, index);
    }

    public Tier getTier() {
        return tier;
    }

    public int getIndex() {
        return index;
    }
}