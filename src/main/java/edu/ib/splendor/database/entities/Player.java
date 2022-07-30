package edu.ib.splendor.database.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {
    private Long id;
    private ArrayList<Card> deck = new ArrayList<>();
    private ArrayList<Card> reserve = new ArrayList<>();
    private final ArrayList<Aristocrat> aristocrats = new ArrayList<>();
    private final HashMap<Gem, Integer> possession = new HashMap<>();
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
        collectLimit = 3;
        taken = new HashMap<>();
    }

    public void addGem(Gem gem, int amount){
        possession.put(gem, possession.getOrDefault(gem, 0) + amount);
    }

    public void addGem(Gem gem){
        addGem(gem, 1);
    }

    public void removeGem(Gem gem, int amount){
        possession.put(gem, possession.getOrDefault(gem, 0) - amount);
    }

    public void removeGem(Gem gem){
        removeGem(gem, 1);
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

    public Player(String name, int red, int green, int blue, int brown, int white, int gold, ArrayList<Card> deck, ArrayList<Card> reserve) {
        possession.put(Gem.RED, red);
        possession.put(Gem.GREEN, green);
        possession.put(Gem.BLUE, blue);
        possession.put(Gem.BROWN, brown);
        possession.put(Gem.WHITE, white);
        possession.put(Gem.GOLD, gold);
        this.name = name;
        this.deck = deck;
        this.reserve = reserve;
        updateStates();
    }

    public Player(String name, Integer gold) {
        this.name = name;
        possession.put(Gem.RED, 0);
        possession.put(Gem.GREEN, 0);
        possession.put(Gem.BLUE, 0);
        possession.put(Gem.BROWN, 0);
        possession.put(Gem.WHITE, 0);
        possession.put(Gem.GOLD, gold);
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

    public ArrayList<Card> getReserve(){
        return reserve;
    }

    public void addReserve(Card card){
        if (reserve.size()<3) reserve.add(card);
    }
    public void removeReserve(Card card){
        reserve.remove(card);
    }


    private void updateStates(){
        points = 0;
        production = new HashMap<>();
        for (Card card: deck){
            production.put(card.getProduction(), production.getOrDefault(card.getProduction(),0)+1);
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

    public HashMap<Gem, Integer> getPossession() {
        return possession;
    }

    public HashMap<Gem, Integer> getProduction() {
        return production;
    }

    public int getPoints() {
        return points;
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

    public ArrayList<Aristocrat> getAristocrats() {
        return aristocrats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
