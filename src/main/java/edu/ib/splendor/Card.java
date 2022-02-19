package edu.ib.splendor;

import java.util.HashMap;

public class Card {
    private Tier tier;
    private HashMap<Gem, Integer> cost = new HashMap<>();
    private String picture;

    public Card(Tier tier, int RED, int GREEN, int BLUE, int BROWN, int WHITE, Gem production, int points, String picture) {
        this.tier = tier;
        this.picture = picture;
        cost.put(Gem.RED, RED);
        cost.put(Gem.GREEN, GREEN);
        cost.put(Gem.BLUE, BLUE);
        cost.put(Gem.BROWN, BROWN);
        cost.put(Gem.WHITE, WHITE);
        this.points = points;
        this.production = production;
    }

    public String getPicture() {
        return picture;
    }

    private int points = 0;
    private final Gem production;

    public int getPoints() {
        return points;
    }

    public Tier getTier() {
        return tier;
    }


    public HashMap<Gem, Integer> getCost() {
        return cost;
    }

    public Gem getProduction() {
        return production;
    }

}
