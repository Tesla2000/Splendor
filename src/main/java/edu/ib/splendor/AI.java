package edu.ib.splendor;

import java.util.*;

public class AI {
    private static HashMap<Integer, Move> possibleMoves;

    public AI() {
        possibleMoves = new HashMap<>();
        int counter = 0;
        for (Tier tier : Tier.values()) {
            int loops;
            if (tier.equals(Tier.RESERVE)) loops = 3;
            else loops = 4;
            for (int i = 0; i < loops; i++) {
                possibleMoves.put(counter, new BuildBuilding(tier, i));
                counter++;
            }
            for (int i = 0; i < 5; i++) {
                if (!tier.equals(Tier.RESERVE)) {
                    possibleMoves.put(counter, new ReserveBuilding(tier, i));
                    counter++;
                }
            }
        }
    }

    public static HashMap<Integer, Move> getPossibleMoves() {
        return possibleMoves;
    }
}
