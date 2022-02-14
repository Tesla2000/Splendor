package edu.ib.splendor;

import java.util.ArrayList;

public class TradeRow {
    private ArrayList<Card> tierFirstHidden;
    private ArrayList<Card> tierSecondHidden;
    private ArrayList<Card> tierThirdHidden;
    private Card[] tierFirstVisible;
    private Card[] tierSecondVisible;
    private Card[] tierThirdVisible;

    public TradeRow(ArrayList<Card> tierFirstHidden, ArrayList<Card> tierSecondHidden, ArrayList<Card> tierThirdHidden, Card[] tierFirstVisible, Card[] tierSecondVisible, Card[] tierThirdVisible) {
        this.tierFirstHidden = tierFirstHidden;
        this.tierSecondHidden = tierSecondHidden;
        this.tierThirdHidden = tierThirdHidden;
        this.tierFirstVisible = tierFirstVisible;
        this.tierSecondVisible = tierSecondVisible;
        this.tierThirdVisible = tierThirdVisible;
    }

    public void takeCard(Tier tier, int index, Player player){
        Card card;
        if (tier.equals(Tier.FIRST)) {
            card = tierFirstVisible[index];
            if (tierFirstHidden.size()>0) {
                tierFirstVisible[index] = tierFirstHidden.get(0);
                tierFirstHidden.remove(0);
            } else tierFirstVisible[index] = null;
        }
        else if (tier.equals(Tier.SECOND)) {
            card = tierSecondVisible[index];
            if (tierSecondHidden.size()>0) {
                tierSecondVisible[index] = tierSecondHidden.get(0);
                tierSecondHidden.remove(0);
            } else tierSecondVisible[index] = null;
        }
        else {
            card = tierThirdVisible[index];
            if (tierThirdHidden.size()>0) {
                tierThirdVisible[index] = tierThirdHidden.get(0);
                tierThirdHidden.remove(0);
            } else tierThirdVisible[index] = null;
        }
        player.deck.add(card);


    }
}
