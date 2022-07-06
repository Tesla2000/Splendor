package edu.ib.splendor;

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
        Random random = new Random();
        int index;
        for (int i = 0; i < 4; i++) {
            for (Tier tier : Tier.values())
                if (!tier.equals(Tier.RESERVE)) {
                    index = random.nextInt(tierFirstHidden.size());
                    cardsVisible.get(Tier.FIRST).add(cardsHidden.get(Tier.FIRST).remove(index));
                }
        }
    }

    public Card takeCard(Tier tier, int index) {
        if (tier.equals(Tier.RESERVE)) throw new IllegalArgumentException("Tier can't be reserve");
        Card card = null;
        if (index == -1) card = cardsHidden.get(tier).remove(0);
        else if (cardsVisible.get(tier).size()>index) card = cardsVisible.get(tier).remove(index);
        return card;
    }

    public Card getCard(Tier tier, int index) {
        if (tier.equals(Tier.RESERVE)) throw new IllegalArgumentException("Tier can't be reserve");
        Card card = null;
        if (index == -1) card = cardsHidden.get(tier).remove(0);
        else if (cardsVisible.get(tier).size()>index) card = cardsVisible.get(tier).get(index);
        return card ;
    }
}
