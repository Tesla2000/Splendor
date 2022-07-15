package edu.ib.splendor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AI {
    private static boolean won;
    private int masterCounter;
    private static boolean lost;
    private static final BoardController boardController = new BoardController();
    private static HashMap<Integer, Move> possibleMoves = null;
    private final ArrayList<Player> players;

    public AI(ArrayList<Player> players, int masterCounter) {
        this.players = players;
        this.masterCounter = masterCounter;
        possibleMoves = new HashMap<>();
        int counter = 0;
        for (Tier tier : Tier.values()) {
            int loops;
            if (tier.equals(Tier.RESERVE)) loops = 3;
            else loops = 4;
            for (int i = 0; i < loops; i++) {
                possibleMoves.put(counter, new BuildBuilding(boardController, tier, i));
                counter++;
            }
            for (int i = 0; i < 5; i++) {
                if (!tier.equals(Tier.RESERVE)) {
                    possibleMoves.put(counter, new ReserveBuilding(boardController, tier, i));
                    counter++;
                }
            }
        }
    }
}
