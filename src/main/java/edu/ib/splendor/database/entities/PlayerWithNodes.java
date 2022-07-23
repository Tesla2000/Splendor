package edu.ib.splendor.database.entities;

import java.util.ArrayList;

public class PlayerWithNodes extends Player {
    private final ArrayList<Node> nodes;
    private String nodesFile;
    private final Player player;

    public PlayerWithNodes(Player player, ArrayList<Node> nodes, String nodesFile) {
        this.player = player;
        this.nodes = nodes;
        this.nodesFile = nodesFile;
    }

    public PlayerWithNodes(Player player, ArrayList<Node> nodes) {
        this.player = player;
        this.nodes = nodes;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    public String getNodesFile() {
        return nodesFile;
    }
}
