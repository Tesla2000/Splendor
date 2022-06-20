package edu.ib.splendor;

import java.util.List;

public class GetGem implements Move{
    Gem[] gemList;
    private final BoardController boardController;

    public GetGem(Gem[] gemList, BoardController boardController) {
        this.gemList = gemList;
        this.boardController = boardController;
    }

    @Override
    public void play(Board board, Player player) {
        for (Gem gem: gemList)
        boardController.collectGem(gem, board, player);
    }

    public BoardController getBoardController() {
        return boardController;
    }

    public Gem[] getGemList() {
        return gemList;
    }
}
