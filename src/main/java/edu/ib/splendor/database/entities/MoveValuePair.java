package edu.ib.splendor.database.entities;

public record MoveValuePair(double value, int move) {
    @Override
    public double value() {
        return value;
    }

    @Override
    public int move() {
        return move;
    }
}
