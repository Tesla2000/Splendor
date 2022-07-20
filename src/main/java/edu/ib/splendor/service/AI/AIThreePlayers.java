package edu.ib.splendor.service.AI;

import edu.ib.splendor.database.entities.Node;
import edu.ib.splendor.database.entities.NumberOfPlayers;
import edu.ib.splendor.database.entities.Player;
import edu.ib.splendor.database.entities.PlayerWithNodes;

import java.io.*;
import java.util.*;

public class AIThreePlayers extends AI {
    public AIThreePlayers(int masterCounter) {
        super(masterCounter);
    }

    public static void main(String[] args) throws IOException {
        AIThreePlayers ai = new AIThreePlayers(10);
//        int betterIndicator = 42; // for 100 trials
        int betterIndicator = 358; // for 1000 trails
        AIController.trainAI(ai, betterIndicator, NumberOfPlayers.three);
    }

    @Override
    protected void setOrder(ArrayList<ArrayList<Node>> allPlayers, int masterCounter, ArrayList<PlayerWithNodes> currentPlayers, int id) throws IOException {
        ArrayList<Node> master = AIController.readNodesFromFile("masters/three/" + masterCounter + ".txt");
        ArrayList<Node> previousMaster = AIController.readNodesFromFile("masters/three/" + Math.max(0,masterCounter-1) + ".txt");
        setCurrentPlayers(new ArrayList<>());
        getCurrentPlayers().add(new PlayerWithNodes(new Player("Master"), master));
        getCurrentPlayers().add(new PlayerWithNodes(new Player("PreviousMaster"), previousMaster));
        getCurrentPlayers().add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
        Collections.shuffle(getCurrentPlayers());
    }
}
