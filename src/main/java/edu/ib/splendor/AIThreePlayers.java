package edu.ib.splendor;

import java.io.*;
import java.util.*;

public class AIThreePlayers extends AI {
    public AIThreePlayers(int masterCounter) {
        super(masterCounter);
    }

    public static void main(String[] args) throws IOException {
        AIThreePlayers ai = new AIThreePlayers(0);
        int betterIndicator = 41;
        AIController.trainAI(ai, betterIndicator);
    }

    @Override
    protected void setOrder(ArrayList<ArrayList<Node>> allPlayers, int masterCounter, ArrayList<PlayerWithNodes> currentPlayers, int id) throws IOException {
        ArrayList<Node> master = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + masterCounter + ".txt");
        ArrayList<Node> previousMaster = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + (masterCounter-1) + ".txt");
        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
        currentPlayers.add(new PlayerWithNodes(new Player("PreviousMaster"), previousMaster));
        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
        Collections.shuffle(currentPlayers);
    }
}
