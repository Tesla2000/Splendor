package edu.ib.splendor.database.entities;

import java.util.ArrayList;

public class PlayerWithNodes extends Player {
    private final ArrayList<Node> nodes;
    private String nodesFile;

    public PlayerWithNodes(Player player, ArrayList<Node> nodes, String nodesFile) {
        super(player.getName(), player.getPossession().get(Gem.RED), player.getPossession().get(Gem.GREEN), player.getPossession().get(Gem.BLUE), player.getPossession().get(Gem.BROWN), player.getPossession().get(Gem.WHITE), player.getPossession().get(Gem.GOLD), player.getDeck(), player.getReserve());
        this.nodes = nodes;
        this.nodesFile = nodesFile;
    }

    public PlayerWithNodes(Player player, ArrayList<Node> nodes) {
        super(player.getName(), player.getPossession().get(Gem.RED), player.getPossession().get(Gem.GREEN), player.getPossession().get(Gem.BLUE), player.getPossession().get(Gem.BROWN), player.getPossession().get(Gem.WHITE), player.getPossession().get(Gem.GOLD), player.getDeck(), player.getReserve());
        this.nodes = nodes;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public String getNodesFile() {
        return nodesFile;
    }
}
