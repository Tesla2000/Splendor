package edu.ib.splendor.database.entities;

import edu.ib.splendor.database.entities.Gem;

import java.io.File;
import java.util.HashMap;

public class Aristocrat {
    private final int red;
    private final int green;
    private final int blue;
    private final int brown;
    private final int white;
    private final String image;

    public Aristocrat(int red, int green, int blue, int brown, int white, String image) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.brown = brown;
        this.white = white;
        this.image = image;
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

    public String getImage(){
        if (image.contains("\\")) return image;
        return new File("src/main/java/edu/ib/splendor/database/pictures/aristocrats/"+image+".png").getAbsolutePath();}
}
