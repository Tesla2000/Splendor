package edu.ib.splendor;

public class Card {
    private Tier tier;
    private int index;
    private final int RED;
    private final int GREEN;
    private final int BLUE;
    private final int BROWN;
    private final int WHITE;

    public Card(Tier tier, int index, int RED, int GREEN, int BLUE, int BROWN, int WHITE, int points, Gem production) {
        this.tier = tier;
        this.index = index;
        this.RED = RED;
        this.GREEN = GREEN;
        this.BLUE = BLUE;
        this.BROWN = BROWN;
        this.WHITE = WHITE;
        this.points = points;
        this.production = production;
    }

    private int points = 0;
    private final Gem production;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Tier getTier() {
        return tier;
    }

    public int getIndex() {
        return index;
    }

    public int getRED() {
        return RED;
    }

    public int getGREEN() {
        return GREEN;
    }

    public int getBLUE() {
        return BLUE;
    }

    public int getBROWN() {
        return BROWN;
    }

    public int getWHITE() {
        return WHITE;
    }

    public Gem getProduction() {
        return production;
    }

    public Card(Tier tier, int index, int RED, int GREEN, int BLUE, int BROWN, int WHITE, Gem production) {
        this.tier = tier;
        this.index = index;
        this.RED = RED;
        this.GREEN = GREEN;
        this.BLUE = BLUE;
        this.BROWN = BROWN;
        this.WHITE = WHITE;
        this.production = production;
    }
}
