package edu.ib.splendor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AIFourPlayers extends AI {
    public static void main(String[] args) throws IOException {
        int masterCounter = 0; 
        while (true) {
            double[] scores = new double[16];
            int best = 0;
            double bestScore = 0;
            CommunicationController.waitForJavaTurn();
            ArrayList<ArrayList<Node>> allPlayers = new ArrayList<>();
            for (int id = 0; id < 16; id++) {
                allPlayers.add(AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\coefficients\\" + id + ".txt"));
            }
            ArrayList<Node> master = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + masterCounter + ".txt");
            ArrayList<Node> previousMaster = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + (masterCounter-1) + ".txt");
            ArrayList<PlayerWithNodes> currentPlayers;
            for (int id = 0; id < allPlayers.size(); id++) {
                for (int i = 0; i < 50; i++) {
                    currentPlayers = new ArrayList<>();
                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
                    currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    currentPlayers.add(new PlayerWithNodes(new Player("PreviousMaster"), previousMaster));
                    currentPlayers.add(new PlayerWithNodes(new Player("PreviousPreviousMaster"), previousMaster));
                    currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
                    Collections.shuffle(currentPlayers);
                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
                    int moves = 0;
                    Board board = new Board(tradeRow, new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList()), 7,7,7,7,7,5);
                    while (true) {
                        moves++;
                        try {
                            AIController.playTurn(players, currentPlayers.get(0).getNodes(), board, AI.getPossibleMoves());
                        } catch (GameLostException e) {
                            break;
                        }
                        if (currentPlayers.get(0).getPoints()>=15) {
                            if (currentPlayers.get(0).getPlayer().getName().equals("Pretender")) {
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
            }
            bestScore = 0;
                for (int i = 0; i < 100; i++) {
                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
                    currentPlayers = new ArrayList<>();
                    currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    currentPlayers.add(new PlayerWithNodes(new Player("PreviousMaster"), previousMaster));
                    currentPlayers.add(new PlayerWithNodes(new Player("PreviousPreviousMaster"), previousMaster));
                    currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(best)));
                    Collections.shuffle(currentPlayers);
                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
                    Board board = new Board(tradeRow, new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList()), 7,7,7,7,7,5);
                    while (true) {
                        try {
                            AIController.playTurn(players, currentPlayers.get(0).getNodes(), board, AI.getPossibleMoves());
                        } catch (GameLostException e) {
                            break;
                        }
                        if (currentPlayers.get(0).getPoints()>=15) {
                            if (currentPlayers.get(0).getPlayer().getName().equals("Pretender")) {
                                bestScore++;
                            }
                            break;
                        }
                        currentPlayers.add(currentPlayers.remove(0));
                        players.add(players.remove(0));
                    }
                }
            if (bestScore > 30) {  // before i find better indicator
                System.out.println("New master "+(masterCounter+1)+": " + (int) Math.round(bestScore) + "/" + 100);
                masterCounter++;
                AIController.saveAsMaster(best, masterCounter);
            }
            System.out.println("Best pretender: " + (int) Math.round(bestScore) + "/" + 100);
            CommunicationController.respondToPython(scores);
            CommunicationController.passToPython();
            }
    }
}
