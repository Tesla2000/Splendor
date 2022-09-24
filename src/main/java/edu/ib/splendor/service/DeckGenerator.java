package edu.ib.splendor.service;

import edu.ib.splendor.database.entities.Aristocrat;
import edu.ib.splendor.database.entities.Cart;
import edu.ib.splendor.database.entities.Gem;
import edu.ib.splendor.database.entities.Tier;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckGenerator {
    public static ArrayList<ArrayList<Cart>> generateCards(){
        ArrayList<ArrayList<Cart>> cards = new ArrayList<>();
        ArrayList<Cart> first = new ArrayList<>();
        ArrayList<Cart> second = new ArrayList<>();
        ArrayList<Cart> third = new ArrayList<>();
        File file = new File("src/main/java/edu/ib/splendor/database/buildings.txt");
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            Tier tier;
            Gem production;
            int points;
            String picture;
            int white;
            int blue;
            int green;
            int red;
            int brown;
            Cart cart;
            while ((line= bufferedReader.readLine())!=null){
                String[] words = line.split("\t");
                if (words[1].equals("black")) production=Gem.BROWN;
                else if (words[1].equals("blue")) production=Gem.BLUE;
                else if (words[1].equals("white")) production=Gem.WHITE;
                else if (words[1].equals("green")) production=Gem.GREEN;
                else production=Gem.RED;
                red = Integer.parseInt(words[8]);
                green = Integer.parseInt(words[7]);
                blue = Integer.parseInt(words[6]);
                brown = Integer.parseInt(words[4]);
                white = Integer.parseInt(words[5]);
                picture = words[0] + production+ red + green + blue+ brown + white;
//                System.out.println(picture);
                points = Integer.parseInt(words[2]);
                if (words[0].equals("1")) {
                    tier = Tier.FIRST;
                    cart = new Cart(tier,red,green,blue,brown,white,production,points,picture);
                    first.add(cart);
                }
                else if (words[0].equals("2")) {
                    tier = Tier.SECOND;
                    cart = new Cart(tier,red,green,blue,brown,white,production,points,picture);
                    second.add(cart);
                }
                else {
                    tier = Tier.THIRD;
                    cart = new Cart(tier,red,green,blue,brown,white,production,points,picture);
                    third.add(cart);
                }
            }
            reader.close();
            bufferedReader.close();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public static ArrayList<Aristocrat> generateAristocrats(){
        ArrayList<Aristocrat> aristocrats = new ArrayList<>();
        aristocrats.add(new Aristocrat(4,4,0,0,0,"44000"));
        aristocrats.add(new Aristocrat(0,4,4,0,0,"04400"));
        aristocrats.add(new Aristocrat(0,0,4,0,4,"00404"));
        aristocrats.add(new Aristocrat(4,0,0,4,0,"40040"));
        aristocrats.add(new Aristocrat(0,0,0,4,4,"00044"));
        aristocrats.add(new Aristocrat(0,0,3,3,3,"00333"));
        aristocrats.add(new Aristocrat(0,3,3,0,3,"03303"));
        aristocrats.add(new Aristocrat(3,3,0,3,0,"33030"));
        aristocrats.add(new Aristocrat(3,3,3,0,0,"33300"));
        aristocrats.add(new Aristocrat(3,0,0,3,3,"30033"));
        Random random = new Random();
        int seed = random.nextInt(Integer.MAX_VALUE);
//        System.out.println("Aristocrats seed: " + seed);
        Collections.shuffle(aristocrats, new Random(seed));
        return aristocrats;
    }
}
