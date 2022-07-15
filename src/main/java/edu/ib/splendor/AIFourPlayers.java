package edu.ib.splendor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class AIFourPlayers extends AI {
    public AIFourPlayers(int masterCounter) {
        super(masterCounter);
    }

    public static void main(String[] args) throws IOException {
        AIFourPlayers ai = new AIFourPlayers(0);
        int betterIndicator = 31;
        AIController.trainAI(ai, betterIndicator);
    }

    @Override
    protected void setOrder(ArrayList<ArrayList<Node>> allPlayers, int masterCounter, ArrayList<PlayerWithNodes> currentPlayers, int id) throws IOException {
        ArrayList<Node> master = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + Math.max(0, masterCounter) + ".txt");
        ArrayList<Node> previousMaster = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + Math.max(0,masterCounter-1) + ".txt");
        ArrayList<Node> previousPreviousMaster = AIController.readNodesFromFile("C:\\Users\\Dell\\IdeaProjects\\Splendor\\masters\\" + Math.max(0,masterCounter-2) + ".txt");
        currentPlayers.add(new PlayerWithNodes(new Player("Master"), master));
        currentPlayers.add(new PlayerWithNodes(new Player("PreviousMaster"), previousMaster));
        currentPlayers.add(new PlayerWithNodes(new Player("PreviousPreviousMaster"), previousPreviousMaster));
        currentPlayers.add(new PlayerWithNodes(new Player("Pretender"), allPlayers.get(id)));
        Collections.shuffle(currentPlayers);
    }
}
