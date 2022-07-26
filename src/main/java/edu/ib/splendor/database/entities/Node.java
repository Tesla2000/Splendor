package edu.ib.splendor.database.entities;

import java.util.ArrayList;

public record Node(ArrayList<Double> coefficients, Double bias) {
    @Override
    public ArrayList<Double> coefficients() {
        return coefficients;
    }

    @Override
    public Double bias() {
        return bias;
    }
}
