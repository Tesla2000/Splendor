package edu.ib.splendor;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DeckGenerator {
    public static ArrayList<ArrayList<Card>> generateCards(){
        ArrayList<ArrayList<Card>> cards = new ArrayList<>();
        ArrayList<Card> first = new ArrayList<>();
        ArrayList<Card> second = new ArrayList<>();
        ArrayList<Card> third = new ArrayList<>();
        File file = new File("C:\\Users\\Dell\\IdeaProjects\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\buildings.txt");
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
            Card card;
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
                    card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                    first.add(card);
                }
                else if (words[0].equals("2")) {
                    tier = Tier.SECOND;
                    card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                    second.add(card);
                }
                else {
                    tier = Tier.THIRD;
                    card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                    third.add(card);
                }
            }
            reader.close();
            bufferedReader.close();
            Random random = new Random();
            int[] seeds = new int[]{
                    random.nextInt(0, Integer.MAX_VALUE),
                    random.nextInt(0, Integer.MAX_VALUE),
                    random.nextInt(0, Integer.MAX_VALUE),
            };
            System.out.println("First seed: " + seeds[0]);
            System.out.println("Second seed: " + seeds[1]);
            System.out.println("Third seed: " + seeds[2]);
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
        int seed = random.nextInt(0, Integer.MAX_VALUE);
        System.out.println("Aristocrats seed: " + seed);
        Collections.shuffle(aristocrats, new Random(seed));
        return aristocrats;
    }
}
