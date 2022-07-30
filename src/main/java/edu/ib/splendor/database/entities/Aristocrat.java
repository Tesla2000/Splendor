package edu.ib.splendor.database.entities;

import java.util.HashMap;

public record Aristocrat(Long id, int red, int green, int blue, int brown, int white, String image) {

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

    public String getImage(){
        return "/edu/ib/splendor/pictures/aristocrats/"+image.replace("/edu/ib/splendor/pictures/aristocrats/", "").replace(".png", "")+".png";
    }
}
