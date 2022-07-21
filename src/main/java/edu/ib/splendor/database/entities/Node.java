package edu.ib.splendor.database.entities;

import java.util.ArrayList;

public class Node {
    private final ArrayList<Double> coefficients;
    private final double bias;

    public Node(ArrayList<Double> coefficients, double bias) {
        this.coefficients = coefficients;
        this.bias = bias;
    }

    public ArrayList<Double> coefficients() {
        return coefficients;
    }

    public Double bias() {
        return bias;
    }
}
