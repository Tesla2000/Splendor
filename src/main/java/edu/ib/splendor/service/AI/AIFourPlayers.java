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
//public class AIFourPlayers extends AI {
//    public AIFourPlayers(int masterCounter) {
//        super(masterCounter);
//    }
//
//    public static void main(String[] args) throws IOException {
//        AIFourPlayers ai = new AIFourPlayers(0);
////        int betterIndicator = 33; // for 100 trials
//        int betterIndicator = 273; // for 1000 trials
//        AIManager.trainAI(ai, betterIndicator, NumberOfPlayers.four);
//    }
//
//    @Override
//    protected void setOrder(ArrayList<ArrayList<Node>> allPlayers, int masterCounter, ArrayList<PlayerWithNodes> currentPlayers, int id) throws IOException {
//        ArrayList<Node> master = AIManager.readNodesFromFile("masters/four/" + masterCounter + ".txt");
//        ArrayList<Node> previousMaster = AIManager.readNodesFromFile("masters/four/" + Math.max(0,masterCounter - 1) + ".txt");
//        ArrayList<Node> previousPreviousMaster = AIManager.readNodesFromFile("masters/four/" + Math.max(0,masterCounter - 2) + ".txt");
//        setCurrentPlayers(new ArrayList<>());
//        getCurrentPlayers().add(new PlayerWithNodes(new Player("Master"), master));
//        getCurrentPlayers().add(new PlayerWithNodes(new Player("PreviousMaster"), previousMaster));
//        getCurrentPlayers().add(new PlayerWithNodes(new Player("PreviousPreviousMaster"), previousPreviousMaster));
//        getCurrentPlayers().add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
//        Collections.shuffle(getCurrentPlayers());
//    }
//}
