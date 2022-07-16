package edu.ib.splendor.database.entities;

import edu.ib.splendor.service.BoardController;

public class GetGem implements Move {
    Gem[] gemList;

    public GetGem(Gem[] gemList) {
        this.gemList = gemList;
    }

    @Override
    public void play(Board board, Player player) {
        for (Gem gem: gemList)
        BoardController.collectGem(gem, board, player);
    }

    public Gem[] getGemList() {
        return gemList;
    }
}
