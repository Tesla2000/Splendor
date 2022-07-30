package edu.ib.splendor.service;

import edu.ib.splendor.controller.GameController;
import edu.ib.splendor.database.entities.Aristocrat;
import edu.ib.splendor.database.entities.Card;
import edu.ib.splendor.database.entities.Gem;
import edu.ib.splendor.database.entities.Tier;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class DeckGenerator {
    private static Random random = new Random();
    public static ArrayList<ArrayList<Card>> generateCards() throws IOException {
        ArrayList<ArrayList<Card>> cards = new ArrayList<>();
        ArrayList<Card> first = new ArrayList<>();
        ArrayList<Card> second = new ArrayList<>();
        ArrayList<Card> third = new ArrayList<>();
        String[] buildings = IOUtils.toString(Objects.requireNonNull(DeckGenerator.class.getResourceAsStream("/edu/ib/splendor/buildings.txt"))).split("\n");
        Tier tier;
        Gem production;
        int points;
        String picture;
        int white;
        int blue;
        int green;
        int red;
        int brown;
        Card card;
        for (String line: buildings){
            String[] words = line.split("\t");
            if (words[1].equals("black")) production=Gem.BROWN;
            else if (words[1].equals("blue")) production=Gem.BLUE;
            else if (words[1].equals("white")) production=Gem.WHITE;
            else if (words[1].equals("green")) production=Gem.GREEN;
            else production=Gem.RED;
            red = Integer.parseInt(words[8].replace("\r", ""));
            green = Integer.parseInt(words[7]);
            blue = Integer.parseInt(words[6]);
            brown = Integer.parseInt(words[4]);
            white = Integer.parseInt(words[5]);
            picture = words[0] + production+ red + green + blue+ brown + white;
//                System.out.println(picture);
            points = Integer.parseInt(words[2]);
            if (words[0].equals("1")) {
                tier = Tier.FIRST;
                card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                card.setId(random.nextLong());
                first.add(card);
            }
            else if (words[0].equals("2")) {
                tier = Tier.SECOND;
                card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                card.setId(random.nextLong());
                second.add(card);
            }
            else {
                tier = Tier.THIRD;
                card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                card.setId(random.nextLong());
                third.add(card);
            }
        }
        Random random = new Random();
        int[] seeds = new int[]{
                random.nextInt(Integer.MAX_VALUE),
                random.nextInt(Integer.MAX_VALUE),
                random.nextInt(Integer.MAX_VALUE),
        };
//            System.out.println("First seed: " + seeds[0]);
//            System.out.println("Second seed: " + seeds[1]);
//            System.out.println("Third seed: " + seeds[2]);
        Collections.shuffle(first, new Random(seeds[0]));
        Collections.shuffle(second, new Random(seeds[0]));
        Collections.shuffle(third, new Random(seeds[0]));
//            Collections.shuffle(first, new Random(95478210));
//            Collections.shuffle(second, new Random(1969018470));
//            Collections.shuffle(third, new Random(1687456992));
        cards.add(first);
        cards.add(second);
        cards.add(third);
        return cards;
    }

    public static ArrayList<Aristocrat> generateAristocrats(){
        ArrayList<Aristocrat> aristocrats = new ArrayList<>();
        aristocrats.add(new Aristocrat(random.nextLong(), 4,4,0,0,0,"44000"));
        aristocrats.add(new Aristocrat(random.nextLong(),0,4,4,0,0,"04400"));
        aristocrats.add(new Aristocrat(random.nextLong(),0,0,4,0,4,"00404"));
        aristocrats.add(new Aristocrat(random.nextLong(),4,0,0,4,0,"40040"));
        aristocrats.add(new Aristocrat(random.nextLong(),0,0,0,4,4,"00044"));
        aristocrats.add(new Aristocrat(random.nextLong(),0,0,3,3,3,"00333"));
        aristocrats.add(new Aristocrat(random.nextLong(),0,3,3,0,3,"03303"));
        aristocrats.add(new Aristocrat(random.nextLong(),3,3,0,3,0,"33030"));
        aristocrats.add(new Aristocrat(random.nextLong(),3,3,3,0,0,"33300"));
        aristocrats.add(new Aristocrat(random.nextLong(),3,0,0,3,3,"30033"));
        int seed = random.nextInt(Integer.MAX_VALUE);
//        System.out.println("Aristocrats seed: " + seed);
        Collections.shuffle(aristocrats, new Random(seed));
        return aristocrats;
    }
}
