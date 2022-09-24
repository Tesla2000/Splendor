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

    public Board(TradeRow tradeRow, ArrayList<Player> players, ArrayList<Aristocrat> aristocrats, int red, int green, int blue, int brown, int white, int gold) {
        this.tradeRow = tradeRow;
        this.players = players;
        this.aristocrats = aristocrats;
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
    
    public Board deepcopy(){
        return new Board(new TradeRow(
                new ArrayList<>(tradeRow.getCardsHidden().get(Tier.FIRST)),
                new ArrayList<>(tradeRow.getCardsHidden().get(Tier.SECOND)),
                new ArrayList<>(tradeRow.getCardsHidden().get(Tier.THIRD)),
                new ArrayList<>(tradeRow.getCardsVisible().get(Tier.FIRST)),
                new ArrayList<>(tradeRow.getCardsVisible().get(Tier.SECOND)),
                new ArrayList<>(tradeRow.getCardsVisible().get(Tier.THIRD))
                ),
                new ArrayList<>(players.stream().map(Player::deepcopy).toList()),
                new ArrayList<>(aristocrats),
                stored.get(Gem.RED),
                stored.get(Gem.GREEN),
                stored.get(Gem.BLUE),
                stored.get(Gem.BROWN),
                stored.get(Gem.WHITE),
                stored.get(Gem.GOLD)
                );
    }
}
