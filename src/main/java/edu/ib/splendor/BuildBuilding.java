package edu.ib.splendor;

public class BuildBuilding implements Move{
    private final BoardController boardController;
    private final Tier tier;
    private final int index;

    public BuildBuilding(BoardController boardController, Tier tier, int index) {
        this.boardController = boardController;
        this.tier = tier;
        this.index = index;
    }

    @Override
    public void play(Board board, Player player) {
        boardController.buyEstate(tier,index,board,player);
    }

    public Tier getTier() {
        return tier;
    }

    public int getIndex() {
        return index;
    }
}
