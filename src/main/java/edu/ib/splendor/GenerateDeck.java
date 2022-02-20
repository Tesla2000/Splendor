package edu.ib.splendor;

import java.io.*;
import java.util.ArrayList;

public class GenerateDeck {
    public static void main(String[] args) {
        generate();
    }
    public static ArrayList<ArrayList<Card>> generate(){
        ArrayList<ArrayList<Card>> cards = new ArrayList<>();
        cards.add(new ArrayList<>());
        cards.add(new ArrayList<>());
        cards.add(new ArrayList<>());
        File file = new File("C:\\Users\\Dell\\Splendor\\src\\main\\java\\edu\\ib\\splendor\\buildings.txt");
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
                if (words[1].equals("blue")) production=Gem.BLUE;
                if (words[1].equals("white")) production=Gem.WHITE;
                if (words[1].equals("green")) production=Gem.GREEN;
                else production=Gem.RED;
                red = Integer.parseInt(words[7]);
                green = Integer.parseInt(words[6]);
                blue = Integer.parseInt(words[5]);
                brown = Integer.parseInt(words[8]);
                white = Integer.parseInt(words[4]);
                picture = words[3];
                points = Integer.parseInt(words[2]);
                if (words[0].equals("1")) {
                    tier = Tier.FIRST;
                    card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                    cards.get(0).add(card);
                }
                else if (words[0].equals("2")) {
                    tier = Tier.SECOND;
                    card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                    cards.get(1).add(card);
                }
                else {
                    tier = Tier.THIRD;
                    card = new Card(tier,red,green,blue,brown,white,production,points,picture);
                    cards.get(2).add(card);
                }
                System.out.println(card);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cards;
    }
}
