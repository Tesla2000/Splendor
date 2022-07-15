package edu.ib.splendor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AITwoPlayers extends AI {
    public AITwoPlayers((ArrayList<Player> players, int masterCounter)) {
        super(players, masterCounter);
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            Random random = new Random();
            double[] scores = new double[16];
            int best = 0;
            double bestScore = 0;
            waitForMyTurn();
            ArrayList<ArrayList<Node>> allPlayers = new ArrayList<>();
            for (int id = 0; id < 16; id++) {
                allPlayers.add(readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\coefficients\\" + id + ".txt"));
            }
            ArrayList<Node> master = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + masterCounter + ".txt");
            ArrayList<PlayerWithNodes> currentPlayers;
            for (int id = 0; id < allPlayers.size(); id++) {
                for (int i = 0; i < 50; i++) {
                    lost = false;
                    won = false;
                    currentPlayers = new ArrayList<>();
                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
                    if (random.nextBoolean()) {
                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
                    } else {
                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    }
                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
                    AITwoPlayers ai = new AITwoPlayers(players);
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
                    if (random.nextBoolean()) {
                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(best)));
                    } else {
                    currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(best)));
                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
                    }
                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
                    Board board = new Board(tradeRow, new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList()), 7,7,7,7,7,5);
                    AITwoPlayers ai = new AITwoPlayers(players);
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
            if (bestScore > 60) {
                System.out.println("New master "+(masterCounter+1)+": " + (int) Math.round(bestScore) + "/" + 100);
                saveAsMaster(best);
            }
                System.out.println("Best pretender: " + (int) Math.round(bestScore) + "/" + 100);
            CommunicationController.respondToPython(scores);
            CommunicationController.passToPython();
            }
    }

//    public static void main(String[] args) throws IOException {
//        Random random = new Random();
//        for (int masterCounter = 1; masterCounter < 29; masterCounter++) {
//            ArrayList<Node> master = readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + masterCounter + ".txt");
//            for (int m = masterCounter+1; m < 29; m++) {
//                int bestScore = 0;
//                for (int i = 0; i < 1000; i++) {
//                    won = false;
//                    lost = false;
//                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
//                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
//                    ArrayList<PlayerWithNodes> currentPlayers = new ArrayList<>();
//                    if (random.nextBoolean()) {
//                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
//                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + m + ".txt")));
//                    } else {
//                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + m + ".txt")));
//                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
//                    }
//                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
//                    AITwoPlayers ai = new AITwoPlayers(new BoardController(), players, new Board(tradeRow, players, 7, 7, 7, 7, 7, 5));
//                    while (true) {
//                        ai.playTurn(currentPlayers.get(0).getNodes());
//                        if (lost) {
//                            break;
//                        }
//                        if (won) {
//                            if (currentPlayers.get(0).getPlayer().getName().equals("Pretender")) {
//                                bestScore++;
//                            }
//                            break;
//                        }
//                        players.add(players.remove(0));
//                        currentPlayers.add(currentPlayers.remove(0));
//                    }
//                }
//                System.out.println("Best pretender " + m + "/" + masterCounter + ": " + bestScore + "/" + 1000);
//            }
//        }
//    }
}
