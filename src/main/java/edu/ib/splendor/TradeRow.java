package edu.ib.splendor;

import java.util.ArrayList;
import java.util.Random;

public class TradeRow {
    private ArrayList<Card> tierFirstHidden;
    private ArrayList<Card> tierSecondHidden;
    private ArrayList<Card> tierThirdHidden;
    private Card[] tierFirstVisible = new Card[4];
    private Card[] tierSecondVisible = new Card[4];
    private Card[] tierThirdVisible = new Card[4];

    public TradeRow(ArrayList<Card> tierFirstHidden, ArrayList<Card> tierSecondHidden, ArrayList<Card> tierThirdHidden) {
        this.tierFirstHidden = tierFirstHidden;
        this.tierSecondHidden = tierSecondHidden;
        this.tierThirdHidden = tierThirdHidden;
        Random random = new Random();
        int index;
        for (int i=0; i<4;i++) {
            index = random.nextInt(tierFirstHidden.size());
            this.tierFirstVisible[i] = this.tierFirstHidden.get(index);
            this.tierFirstHidden.remove(index);
            index = random.nextInt(tierSecondHidden.size());
            this.tierSecondVisible[i] = this.tierSecondHidden.get(index);
            this.tierSecondHidden.remove(index);
            index = random.nextInt(tierThirdHidden.size());
            this.tierThirdVisible[i] = this.tierThirdHidden.get(index);
            this.tierThirdHidden.remove(index);
        }
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
        player.addCard(card);
    }
    public Card getCard(Tier tier, int index){
        Card card;
        if (tier.equals(Tier.FIRST)) card = tierFirstVisible[index];
        else if (tier.equals(Tier.SECOND)) card = tierSecondVisible[index];
        else card = tierThirdVisible[index];
        return card;
    }

}
