package edu.ib.splendor;

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
