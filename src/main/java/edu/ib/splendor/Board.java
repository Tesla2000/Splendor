package edu.ib.splendor;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private TradeRow tradeRow;
    private ArrayList<Player> players;
    private HashMap<Gem, Integer> stored = new HashMap<>();

    public Board(TradeRow tradeRow, ArrayList<Player> players, int red, int green, int blue, int brown, int white, int gold) {
        this.tradeRow = tradeRow;
        this.players = players;
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

    public Board(TradeRow tradeRow, ArrayList<Player> players) {
        this.tradeRow = tradeRow;
        this.players = players;
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
}