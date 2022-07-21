package edu.ib.splendor.database.entities;

public class MoveValuePair {
     private final double value;
     private final int move;

    public MoveValuePair(double value, int move) {
        this.value = value;
        this.move = move;
    }


    public double value() {
        return value;
    }

    public int move() {
        return move;
    }
}
