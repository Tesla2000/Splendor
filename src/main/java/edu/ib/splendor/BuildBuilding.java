package edu.ib.splendor;

public record BuildBuilding(Tier tier,
                            int index) implements Move {

    @Override
    public void play(Board board, Player player) {
        BoardController.buyEstate(tier, index, board, player);
    }

    public Tier getTier() {
        return tier;
    }

    public int getIndex() {
        return index;
    }
}
