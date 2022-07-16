package edu.ib.splendor.database.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TradeRow {
    private final HashMap<Tier, ArrayList<Card>> cardsVisible = new HashMap<>();
    private final HashMap<Tier, ArrayList<Card>> cardsHidden = new HashMap<>();

    public TradeRow(ArrayList<Card> tierFirstHidden, ArrayList<Card> tierSecondHidden, ArrayList<Card> tierThirdHidden) {
        this.cardsHidden.put(Tier.FIRST, tierFirstHidden);
        this.cardsHidden.put(Tier.SECOND, tierSecondHidden);
        this.cardsHidden.put(Tier.THIRD, tierThirdHidden);
        cardsVisible.put(Tier.FIRST, new ArrayList<>());
        cardsVisible.put(Tier.SECOND, new ArrayList<>());
        cardsVisible.put(Tier.THIRD, new ArrayList<>());
        Random random = new Random();
        int index;
        for (int i = 0; i < 4; i++) {
            for (Tier tier : Tier.values())
                if (!tier.equals(Tier.RESERVE)) {
                    cardsVisible.get(tier).add(cardsHidden.get(tier).remove(0));
                }
        }
    }

    public Card takeCard(Tier tier, int index) {
        if (tier.equals(Tier.RESERVE)) throw new IllegalArgumentException("Tier can't be reserve");
        Card card = null;
        if (index == -1) card = cardsHidden.get(tier).remove(0);
        else if (cardsVisible.get(tier).size()>index) {
            card = cardsVisible.get(tier).remove(index);
            if (cardsHidden.get(tier).size() > 0) cardsVisible.get(tier).add(cardsHidden.get(tier).remove(0));
        }
        return card;
    }

    public Card getCard(Tier tier, int index) {
        if (tier.equals(Tier.RESERVE)) throw new IllegalArgumentException("Tier can't be reserve");
        Card card = null;
        if (index == -1) card = cardsHidden.get(tier).remove(0);
        else if (cardsVisible.get(tier).size()>index) card = cardsVisible.get(tier).get(index);
        return card ;
    }

    public HashMap<Tier, ArrayList<Card>> getCardsVisible() {
        return cardsVisible;
    }

    public HashMap<Tier, ArrayList<Card>> getCardsHidden() {
        return cardsHidden;
    }
}
