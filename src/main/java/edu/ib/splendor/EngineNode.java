package edu.ib.splendor;

import edu.ib.splendor.database.entities.Board;
import edu.ib.splendor.database.entities.Move;

import java.util.ArrayList;

public class EngineNode {
    private Board board;
    private EngineNode parent;
    private ArrayList<EngineNode> children;
    private ArrayList<Move> moves;

    public EngineNode(Board board, EngineNode parent, ArrayList<EngineNode> children, ArrayList<Move> moves) {
        this.board = board;
        this.parent = parent;
        this.children = children;
        this.moves = moves;
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

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }
}
