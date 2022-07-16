package edu.ib.splendor.database.entities;

import edu.ib.splendor.service.BoardController;

public record ReserveBuilding(Tier tier,
                              int index) implements Move {

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