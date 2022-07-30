package edu.ib.splendor.database.entities;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Objects;

public class Card {
    private Tier tier;
    private HashMap<Gem, Integer> cost = new HashMap<>();
    private String picture;
    private Long id;

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
        return "/edu/ib/splendor/pictures/buildings/"+picture+".png";
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

    @Override
    public String toString() {
        return "Card{" +
                "tier=" + tier +
                ", cost=" + cost +
                ", picture='" + picture + '\'' +
                ", points=" + points +
                ", production=" + production +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
