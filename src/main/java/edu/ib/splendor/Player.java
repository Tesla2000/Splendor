package edu.ib.splendor;

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> deck = new ArrayList<>();
    private Card[] reserve = new Card[3];
    private ArrayList<Aristocrat> aristocrats = new ArrayList<>();
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private int brown = 0;
    private int white = 0;
    private int gold = 0;
    private int redProduction = 0;
    private int greenProduction = 0;
    private int blueProduction = 0;
    private int brownProduction = 0;
    private int whiteProduction = 0;
    private int points = 0;
    private int collectLimit = 0;

    public Card[] getReserve(){
        return reserve;
    }

    public void insertReserve(Card card){
        for (int i = 0; i<3; i++){
            if (reserve[i] == null){
                reserve[i] = card;
                break;
            }
        }
    }
    public void removeReserve(Card card){
        Card[] newReserve = new Card[3];
        int j = 0;
        for (int i = 0; i<3; i++){
            if (reserve[i] != null && !reserve[i].equals(card)){
                newReserve[j] = reserve[i];
                j++;
            }
        }
        reserve = newReserve;
    }

    private void updateStates(){
        redProduction = 0;
        greenProduction = 0;
        blueProduction = 0;
        brownProduction = 0;
        whiteProduction = 0;
        points = 0;
        for (Card card: deck){
            if (card.getProduction().equals(Gem.RED)) redProduction++;
            if (card.getProduction().equals(Gem.GREEN)) greenProduction++;
            if (card.getProduction().equals(Gem.BLUE)) blueProduction++;
            if (card.getProduction().equals(Gem.BROWN)) brownProduction++;
            if (card.getProduction().equals(Gem.WHITE)) whiteProduction++;
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

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getBrown() {
        return brown;
    }

    public void setBrown(int brown) {
        this.brown = brown;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int white) {
        this.white = white;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getRedProduction() {
        return redProduction;
    }

    public void setRedProduction(int redProduction) {
        this.redProduction = redProduction;
    }

    public int getGreenProduction() {
        return greenProduction;
    }

    public void setGreenProduction(int greenProduction) {
        this.greenProduction = greenProduction;
    }

    public int getBlueProduction() {
        return blueProduction;
    }

    public void setBlueProduction(int blueProduction) {
        this.blueProduction = blueProduction;
    }

    public int getBrownProduction() {
        return brownProduction;
    }

    public void setBrownProduction(int brownProduction) {
        this.brownProduction = brownProduction;
    }

    public int getWhiteProduction() {
        return whiteProduction;
    }

    public void setWhiteProduction(int whiteProduction) {
        this.whiteProduction = whiteProduction;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
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
}
