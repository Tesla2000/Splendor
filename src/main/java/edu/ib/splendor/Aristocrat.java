package edu.ib.splendor;

import java.util.HashMap;

public record Aristocrat(int red, int green, int blue, int brown, int white, String image) {

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getBrown() {
        return brown;
    }

    public int getWhite() {
        return white;
    }

    public int getPoints() {
        return 3;
    }

    public HashMap<Gem, Integer> getCost(){
        HashMap<Gem, Integer> cost = new HashMap<>();
        cost.put(Gem.BLUE, blue);
        cost.put(Gem.RED, red);
        cost.put(Gem.GREEN, green);
        cost.put(Gem.BROWN, brown);
        cost.put(Gem.WHITE, white);
        return cost;
    }

    public String getImage(){return "C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\pictures\\" + image + ".png";}
}
