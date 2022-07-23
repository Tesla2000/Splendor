package edu.ib.splendor.service.AI;

import edu.ib.splendor.database.entities.*;
import edu.ib.splendor.service.CommunicationManager;
import edu.ib.splendor.service.DeckGenerator;
import edu.ib.splendor.service.GameLostException;

import java.io.IOException;
import java.util.*;

public abstract class AI {
    private static final HashMap<Integer, Move> possibleMoves;
    static {
        int counter = 0;
        possibleMoves = new HashMap<>();
        for (Tier tier : Tier.values()) {
            int loops;
            if (tier.equals(Tier.RESERVE)) loops = 3;
            else loops = 4;
            for (int i = 0; i < loops; i++) {
                possibleMoves.put(counter, new BuildBuilding(tier, i));
                counter++;
            }
            for (int i = 0; i < 5; i++) {
                if (!tier.equals(Tier.RESERVE)) {
                    possibleMoves.put(counter, new ReserveBuilding(tier, i));
                    counter++;
                }
            }
        }
    }
    private double[] scores;
    ArrayList<ArrayList<Node>> allPlayers;
    private int best;
    private int masterCounter;
    private double bestScore;
    private ArrayList<Player> players;
    private ArrayList<PlayerWithNodes> currentPlayers;

    public void initializeTraining() throws IOException {
        scores = new double[16];
        best = 0;
        bestScore = 0;
        CommunicationManager.waitForJavaTurn();
        allPlayers = new ArrayList<>();
        for (int id = 0; id < 16; id++) {
            allPlayers.add(AIManager.readNodesFromFile("coefficients/" + id + ".txt"));
        }
    }

    public void playGame(int id){
        ArrayList<ArrayList<Card>> cards = DeckGenerator.generateCards();
        TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
        List<Player> list = new ArrayList<>();
        for (PlayerWithNodes currentPlayer : currentPlayers) {
//            Player player = currentPlayer.getPlayer();
            Player player = currentPlayer;
            list.add(player);
        }
        players = new ArrayList<>(list);
        List<Player> result = new ArrayList<>();
        for (PlayerWithNodes currentPlayer : currentPlayers) {
//            Player player = currentPlayer.getPlayer();
            Player player = currentPlayer;
            result.add(player);
        }
        Board board = new Board(tradeRow, new ArrayList<>(result), 7, 7, 7, 7, 7, 5);
        int moves = 0;
        while (true) {
            moves++;
            try{
                AIManager.playTurn(players, currentPlayers.get(0).getNodes(), board, getPossibleMoves());
            } catch (GameLostException e){
                break;
            }
//            if (currentPlayers.get(0).getPlayer().getPoints()>=15) {
//                if (currentPlayers.get(0).getPlayer().getName().equals("Pretender")) {
            if (currentPlayers.get(0).getPoints()>=15) {
                if (currentPlayers.get(0).getName().equals("Pretender")) {
                    scores[id] = scores[id] + 1 + 1.0 / moves / 50;
                    if (scores[id] > bestScore) {
                        best = id;
                        bestScore = scores[id];
                    }
                }
                break;
            }
            currentPlayers.add(currentPlayers.remove(0));
            players.add(players.remove(0));
        }
    }

    public AI(int masterCounter) {
        this.masterCounter = masterCounter;
    }

    public static HashMap<Integer, Move> getPossibleMoves() {
        return possibleMoves;
    }

    public double[] getScores() {
        return scores;
    }

    public ArrayList<ArrayList<Node>> getAllPlayers() {
        return allPlayers;
    }

    public int getBest() {
        return best;
    }

    public double getBestScore() {
        return bestScore;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<PlayerWithNodes> getCurrentPlayers() {
        return currentPlayers;
    }

    public void setBestScore(double bestScore) {
        this.bestScore = bestScore;
    }

    public int getMasterCounter() {
        return masterCounter;
    }

    public void setMasterCounter(int masterCounter) {
        this.masterCounter = masterCounter;
    }

    public void setScores(double[] scores) {
        this.scores = scores;
    }

    public void setCurrentPlayers(ArrayList<PlayerWithNodes> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    protected abstract void setOrder(ArrayList<ArrayList<Node>> allPlayers, int masterCounter, ArrayList<PlayerWithNodes> currentPlayers, int id) throws IOException;
}
