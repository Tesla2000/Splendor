package edu.ib.splendor.database.entities;

import edu.ib.splendor.service.BoardManager;

import java.util.Objects;

public record BuildBuilding(Tier tier, int index) implements Move {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildBuilding that = (BuildBuilding) o;
        return index == that.index && tier == that.tier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tier, index);
    }
}
