package edu.ib.splendor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Board {
    private TradeRow tradeRow;
    private ArrayList<Player> players;
    private ArrayList<Aristocrat> aristocrats;
    private HashMap<Gem, Integer> stored = new HashMap<>();

    public Board(TradeRow tradeRow, ArrayList<Player> players, int red, int green, int blue, int brown, int white, int gold) {
        this.tradeRow = tradeRow;
        this.players = players;
        aristocrats = GenerateDeck.generateAristocrats();
        Random random = new Random();
        while (aristocrats.size()!=4)
            aristocrats.remove(random.nextInt(aristocrats.size()));
        stored.put(Gem.RED, red);
        stored.put(Gem.GREEN, green);
        stored.put(Gem.BLUE, blue);
        stored.put(Gem.BROWN, brown);
        stored.put(Gem.WHITE, white);
        stored.put(Gem.GOLD, gold);
    }
    public void turn(){
        ArrayList<Player> holder = new ArrayList<>();
        holder.add(players.get(players.size()-1));
        for (int i=0; i<players.size()-1; i++)  holder.add(players.get(i));
        players = holder;
    }

    public Integer getStored(Gem gem){
        return stored.get(gem);
    }

    public void changeStored(Gem gem, Integer value){
        stored.put(gem, Math.max(stored.get(gem)+value, 0));
    }


    public TradeRow getTradeRow() {
        return tradeRow;
    }

    public void setTradeRow(TradeRow tradeRow) {
        this.tradeRow = tradeRow;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Aristocrat> getAristocrats() {
        return aristocrats;
    }
    public void removeAristocrat(Aristocrat aristocrat){
        aristocrats.remove(aristocrat);
    }
}
