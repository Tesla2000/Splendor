package edu.ib.splendor.database.entities;

import edu.ib.splendor.service.DeckGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private TradeRow tradeRow;
    private ArrayList<Player> players;
    private final ArrayList<Aristocrat> aristocrats;
    private final HashMap<Gem, Integer> stored = new HashMap<>();

    public Board(TradeRow tradeRow, ArrayList<Player> players, int red, int green, int blue, int brown, int white, int gold) {
        this.tradeRow = tradeRow;
        this.players = players;
        aristocrats = DeckGenerator.generateAristocrats();
        while (aristocrats.size()!=4)
            aristocrats.remove(aristocrats.get(0));
        stored.put(Gem.RED, red);
        stored.put(Gem.GREEN, green);
        stored.put(Gem.BLUE, blue);
        stored.put(Gem.BROWN, brown);
        stored.put(Gem.WHITE, white);
        stored.put(Gem.GOLD, gold);
    }

    public Integer getStored(Gem gem){
        return stored.get(gem);
    }

    public HashMap<Gem, Integer> getStored(){
        return stored;
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
