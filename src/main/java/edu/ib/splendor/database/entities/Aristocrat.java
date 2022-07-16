package edu.ib.splendor.database.entities;

import edu.ib.splendor.database.entities.Gem;

import java.io.File;
import java.util.HashMap;

public record Aristocrat(int red, int green, int blue, int brown, int white, String image) {

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

    public String getImage(){return new File("src/main/java/edu/ib/splendor/database/pictures/aristocrats/"+image+".png").getAbsolutePath();}
}
