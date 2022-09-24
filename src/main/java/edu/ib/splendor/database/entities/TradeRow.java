package edu.ib.splendor.database.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class TradeRow {
    private final HashMap<Tier, ArrayList<Cart>> cardsVisible = new HashMap<>();
    private final HashMap<Tier, ArrayList<Cart>> cardsHidden = new HashMap<>();

    public TradeRow(ArrayList<Cart> tierFirstHidden, ArrayList<Cart> tierSecondHidden, ArrayList<Cart> tierThirdHidden) {
        this.cardsHidden.put(Tier.FIRST, tierFirstHidden);
        this.cardsHidden.put(Tier.SECOND, tierSecondHidden);
        this.cardsHidden.put(Tier.THIRD, tierThirdHidden);
        cardsVisible.put(Tier.FIRST, new ArrayList<>());
        cardsVisible.put(Tier.SECOND, new ArrayList<>());
        cardsVisible.put(Tier.THIRD, new ArrayList<>());
        for (int i = 0; i < 4; i++) {
            for (Tier tier : Tier.values())
                if (!tier.equals(Tier.RESERVE)) {
                    cardsVisible.get(tier).add(cardsHidden.get(tier).remove(0));
                }
        }
    }

    public TradeRow(ArrayList<Cart> tierFirstHidden, ArrayList<Cart> tierSecondHidden, ArrayList<Cart> tierThirdHidden,
                    ArrayList<Cart> tierFirstVisible, ArrayList<Cart> tierSecondVisible, ArrayList<Cart> tierThirdVisible) {
        this.cardsHidden.put(Tier.FIRST, tierFirstHidden);
        this.cardsHidden.put(Tier.SECOND, tierSecondHidden);
        this.cardsHidden.put(Tier.THIRD, tierThirdHidden);
        cardsVisible.put(Tier.FIRST, tierFirstVisible);
        cardsVisible.put(Tier.SECOND, tierSecondVisible);
        cardsVisible.put(Tier.THIRD, tierThirdVisible);
    }

    public Cart takeCard(Tier tier, int index) {
        if (tier.equals(Tier.RESERVE)) throw new IllegalArgumentException("Tier can't be reserve");
        Cart cart = null;
        if (index == -1) cart = cardsHidden.get(tier).remove(0);
        else if (cardsVisible.get(tier).size()>index) {
            cart = cardsVisible.get(tier).remove(index);
            if (cardsHidden.get(tier).size() > 0) cardsVisible.get(tier).add(cardsHidden.get(tier).remove(0));
        }
        return cart;
    }

    public Cart getCard(Tier tier, int index) {
        if (tier.equals(Tier.RESERVE)) throw new IllegalArgumentException("Tier can't be reserve");
        Cart cart = null;
        if (index == -1) cart = cardsHidden.get(tier).remove(0);
        else if (cardsVisible.get(tier).size()>index) cart = cardsVisible.get(tier).get(index);
        return cart;
    }

    public HashMap<Tier, ArrayList<Cart>> getCardsVisible() {
        return cardsVisible;
    }

    public HashMap<Tier, ArrayList<Cart>> getCardsHidden() {
        return cardsHidden;
    }
}
