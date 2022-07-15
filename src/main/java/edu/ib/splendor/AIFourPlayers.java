package edu.ib.splendor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AIFourPlayers extends AI {
     public AIFourPlayers((ArrayList<Player> players, int masterCounter)) {
        super(players, masterCounter);
    }
    public static void main(String[] args) throws IOException {
        int masterCounter = 0; 
        while (true) {
            Random random = new Random();
            double[] scores = new double[16];
            int best = 0;
            double bestScore = 0;
            waitForMyTurn();
            ArrayList<ArrayList<Node>> allPlayers = new ArrayList<>();
            for (int id = 0; id < 16; id++) {
                allPlayers.add(AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\coefficients\\" + id + ".txt"));
            }
            ArrayList<Node> master = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + masterCounter + ".txt");
            ArrayList<Node> previousMaster = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + (masterCounter-1) + ".txt");
            ArrayList<PlayerWithNodes> currentPlayers;
            for (int id = 0; id < allPlayers.size(); id++) {
                for (int i = 0; i < 50; i++) {
                    lost = false;
                    won = false;
                    currentPlayers = new ArrayList<>();
                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
                    currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    currentPlayers.add(new PlayerWithNodes(new Player("PreviousMaster"), previousMaster));
                    currentPlayers.add(new PlayerWithNodes(new Player("PreviousPreviousMaster"), previousMaster));
                    currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
                    Collections.shuffle(list);
                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
                    AIThreePlayers ai = new AIThreePlayers(players, masterCounter);
                    int moves = 0;
                    Board board = new Board(tradeRow, new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList()), 7,7,7,7,7,5);
                    while (true) {
                        moves++;
                        AIController.playTurn(currentPlayers.get(0).getNodes(), board);
                        if (lost) {
                            break;
                        }
                        if (won) {
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
                    won = false;
                    lost = false;
                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
                    currentPlayers = new ArrayList<>();
                    currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    currentPlayers.add(new PlayerWithNodes(new Player("PreviousMaster"), previousMaster));
                    currentPlayers.add(new PlayerWithNodes(new Player("PreviousPreviousMaster"), previousMaster));
                    currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
                    Collections.shuffle(list);
                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
                    Board board = new Board(tradeRow, new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList()), 7,7,7,7,7,5);
                    AIThreePlayers ai = new AIThreePlayers(players);
                    while (true) {
                        AIController.playTurn(currentPlayers.get(0).getNodes(), board);
                        if (lost) {
                            break;
                        }
                        if (won) {
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
                saveAsMaster(best);
            }
            System.out.println("Best pretender: " + (int) Math.round(bestScore) + "/" + 100);
            CommunicationController.respondToPython(scores);
            CommunicationController.passToPython();
            }
    }
}
