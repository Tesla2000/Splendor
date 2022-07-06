package edu.ib.splendor;

import java.util.ArrayList;

public record PlayerWithNodes(Player player, ArrayList<Node> nodes) {
    @Override
    public Player player() {
        return player;
    }

    @Override
    public ArrayList<Node> nodes() {
        return nodes;
    }
}
