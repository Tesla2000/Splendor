//package edu.ib.splendor.service.AI;
//
//import edu.ib.splendor.database.entities.Node;
//import edu.ib.splendor.database.entities.NumberOfPlayers;
//import edu.ib.splendor.database.entities.Player;
//import edu.ib.splendor.database.entities.PlayerWithNodes;
//
//import java.io.*;
//import java.util.*;
//
//public class AITwoPlayers extends AI {
//
//    public AITwoPlayers(int masterCounter) {
//        super(masterCounter);
//    }
//
//    public static void main(String[] args) throws IOException {
//        AITwoPlayers ai = new AITwoPlayers(28);
//        int betterIndicator = 61;
//        AIManager.trainAI(ai, betterIndicator, NumberOfPlayers.two);
////        while (true) {
////            ai.initializeTraining();
////            for (int id = 0; id < ai.getAllPlayers().size(); id++) {
////                for (int i = 0; i < 50; i++) {
////                    ai.setOrder(ai.getAllPlayers(), ai.getMasterCounter(), ai.getCurrentPlayers(), id);
////                    ai.playGame(id);
////                }
////            }
////            ai.setBestScore(0);
////            for (int i = 0; i < 100; i++) {
////                ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
////                TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
////                currentPlayers = new ArrayList<>();
////                ai.setOrder(allPlayers, master, currentPlayers, best);
////                ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
////                Board board = new Board(tradeRow, new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList()), 7,7,7,7,7,5);
////                while (true) {
////                    try {
////                        AIController.playTurn(players, currentPlayers.get(0).getNodes(), board, getPossibleMoves());
////                    } catch (GameLostException e){
////                        break;
////                    }
////                    if (currentPlayers.get(0).getPoints()>= 15) {
////                        if (currentPlayers.get(0).getPlayer().getName().equals("Pretender")) {
////                            bestScore++;
////                        }
////                        break;
////                    }
////                    currentPlayers.add(currentPlayers.remove(0));
////                    players.add(players.remove(0));
////                ai.setOrder(ai.getAllPlayers(), ai.getMasterCounter(), ai.getCurrentPlayers(), ai.getBest());
////                ai.playGame(ai.getBest());
////            }
////            if (ai.getBestScore() > betterIndicator) {
////                System.out.println("New master " + (ai.getMasterCounter() + 1) + ": " + (int) Math.round(ai.getBestScore()) + "/" + 100);
////                ai.setMasterCounter(ai.getMasterCounter() + 1);
////                AIController.saveAsMaster(ai.getBest(), ai.getMasterCounter());
////            }
////            System.out.println("Best pretender: " + (int) Math.round(ai.getBestScore()) + "/" + 100);
////            CommunicationController.respondToPython(ai.getScores());
////            CommunicationController.passToPython();
////        }
//    }
//
//    @Override
//    protected void setOrder(ArrayList<ArrayList<Node>> allPlayers, int masterCounter, ArrayList<PlayerWithNodes> currentPlayers, int id) throws IOException {
//        ArrayList<Node> master = AIManager.readNodesFromFile("masters/two/" + masterCounter + ".txt");
//        setCurrentPlayers(new ArrayList<>());
//        getCurrentPlayers().add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
//        getCurrentPlayers().add(new PlayerWithNodes(new Player("Master"), master));
//        Collections.shuffle(getCurrentPlayers());
//    }
//
////    public static void main(String[] args) throws IOException {
////        Random random = new Random();
////        for (int masterCounter = 1; masterCounter < 29; masterCounter++) {
////            ArrayList<Node> master = readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + masterCounter + ".txt");
////            for (int m = masterCounter+1; m < 29; m++) {
////                int bestScore = 0;
////                for (int i = 0; i < 1000; i++) {
////                    won = false;
////                    lost = false;
////                    ArrayList<ArrayList<Card>> cards = GenerateDeck.generateCards();
////                    TradeRow tradeRow = new TradeRow(cards.get(0), cards.get(1), cards.get(2));
////                    ArrayList<PlayerWithNodes> currentPlayers = new ArrayList<>();
////                    if (random.nextBoolean()) {
////                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
////                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + m + ".txt")));
////                    } else {
////                        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + m + ".txt")));
////                        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
////                    }
////                    ArrayList<Player> players = new ArrayList<>(currentPlayers.stream().map(PlayerWithNodes::getPlayer).toList());
////                    AITwoPlayers ai = new AITwoPlayers(new BoardController(), players, new Board(tradeRow, players, 7, 7, 7, 7, 7, 5));
////                    while (true) {
////                        ai.playTurn(currentPlayers.get(0).getNodes());
////                        if (lost) {
////                            break;
////                        }
////                        if (won) {
////                            if (currentPlayers.get(0).getPlayer().getName().equals("Pretender")) {
////                                bestScore++;
////                            }
////                            break;
////                        }
////                        players.add(players.remove(0));
////                        currentPlayers.add(currentPlayers.remove(0));
////                    }
////                }
////                System.out.println("Best pretender " + m + "/" + masterCounter + ": " + bestScore + "/" + 1000);
////            }
////        }
////    }
//}
