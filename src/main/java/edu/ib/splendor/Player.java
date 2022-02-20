package edu.ib.splendor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Player {
    private ArrayList<Card> deck = new ArrayList<>();
    private Card[] reserve = new Card[3];
    private ArrayList<Aristocrat> aristocrats = new ArrayList<>();
    private HashMap<Gem, Integer> possession = new HashMap<>();
    private HashMap<Gem, Integer> production = new HashMap<>();
    private int points = 0;
    private String name = "";
    private HashMap<Gem, Boolean> taken = new HashMap<>();
    private int collectLimit = 0;

    public void addToTaken(Gem gem){
        taken.put(gem, true);
    }

    public boolean gemInTaken(Gem gem){
        return taken.getOrDefault(gem, false).equals(true);
    }

    public void clearTaken(){
        taken = new HashMap<>();
    }

    public Player() {
        possession.put(Gem.RED, 0);
        possession.put(Gem.GREEN, 0);
        possession.put(Gem.BLUE, 0);
        possession.put(Gem.BROWN, 0);
        possession.put(Gem.WHITE, 0);
        possession.put(Gem.GOLD, 0);
        production.put(Gem.RED, 0);
        production.put(Gem.GREEN, 0);
        production.put(Gem.BLUE, 0);
        production.put(Gem.BROWN, 0);
        production.put(Gem.WHITE, 0);
    }

    public Player(String name) {
        this.name = name;
        possession.put(Gem.RED, 0);
        possession.put(Gem.GREEN, 0);
        possession.put(Gem.BLUE, 0);
        possession.put(Gem.BROWN, 0);
        possession.put(Gem.WHITE, 0);
        possession.put(Gem.GOLD, 0);
        production.put(Gem.RED, 0);
        production.put(Gem.GREEN, 0);
        production.put(Gem.BLUE, 0);
        production.put(Gem.BROWN, 0);
        production.put(Gem.WHITE, 0);
    }

    public void changeGem(Gem gem, Integer amount){
        possession.put(gem, Math.max(possession.get(gem)-amount, 0));
    }

    public Integer allGems(){
        Integer total = 0;
        for (Integer value: possession.values())
            total += value;
        return total;
    }

    public Card[] getReserve(){
        return reserve;
    }

    public int getReserveNumber(){
        return (int) (3 - Arrays.stream(reserve).filter(Objects::isNull).count());
    }

    public void insertReserve(Card card){
        for (int i = 0; i<3; i++){
            if (reserve[i] == null){
                reserve[i] = card;
                break;
            }
        }
    }
    public void removeReserve(Card card){
        Card[] newReserve = new Card[3];
        int j = 0;
        for (int i = 0; i<3; i++){
            if (reserve[i] != null && !reserve[i].equals(card)){
                newReserve[j] = reserve[i];
                j++;
            }
        }
        reserve = newReserve;
    }
    public void addReserve(Card card){
        for (int i = 0; i<3; i++){
            if (reserve[i] == null){
                reserve[i] = card;
                break;
            }
        }
    }

    private void updateStates(){
        points = 0;
        for (Card card: deck){
            production.put(card.getProduction(), production.get(card.getProduction())+1);
            points += card.getPoints();
        }
        for (Aristocrat aristocrat: aristocrats)
            points += aristocrat.getPoints();
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void addCard(Card card){
        deck.add(card);
        updateStates();
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public HashMap<Gem, Integer> getPossession() {
        return possession;
    }

    public HashMap<Gem, Integer> getProduction() {
        return production;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCollectLimit() {
        return collectLimit;
    }

    public void setCollectLimit(int collectLimit) {
        this.collectLimit = collectLimit;
    }

    public void addAristocrat(Aristocrat aristocrat){
        aristocrats.add(aristocrat);
        updateStates();
    }

    public String getName() {
        return name;
    }
}
