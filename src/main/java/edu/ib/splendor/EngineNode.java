package edu.ib.splendor;

import edu.ib.splendor.database.entities.Board;

import java.util.ArrayList;

public class EngineNode {
    private Board board;
    private EngineNode parent;
    private ArrayList<EngineNode> children;
    private Double value;
    private boolean terminal;
    private Integer lastMove;

    public EngineNode(Board board, EngineNode parent, Integer lastMove) {
        this.board = board;
        this.parent = parent;
        this.children = new ArrayList<>();
        this.value = null;
        this.terminal = false;
        this.lastMove = lastMove;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public EngineNode getParent() {
        return parent;
    }

    public void setParent(EngineNode parent) {
        this.parent = parent;
    }

    public ArrayList<EngineNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<EngineNode> children) {
        this.children = children;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public int getLastMove() {
        return lastMove;
    }

    public void setLastMove(int lastMove) {
        this.lastMove = lastMove;
    }
}
