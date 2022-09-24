package edu.ib.splendor.database.entities;

import edu.ib.splendor.service.BoardManager;

import java.util.Arrays;

public class GetGem implements Move {
    Gem[] gemList;

    public GetGem(Gem[] gemList) {
        this.gemList = gemList;
    }

    @Override
    public void play(Board board, Player player) {
        for (Gem gem: gemList)
        BoardManager.collectGem(gem, board, player);
    }

    public Gem[] getGemList() {
        return gemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetGem getGem = (GetGem) o;
        return Arrays.equals(gemList, getGem.gemList);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(gemList);
    }
}
