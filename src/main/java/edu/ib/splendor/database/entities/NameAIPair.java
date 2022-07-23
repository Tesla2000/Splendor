package edu.ib.splendor.database.entities;

public class NameAIPair {
    private final String name;
    private final boolean isAI;

    public NameAIPair(String name, boolean isAI) {
        this.name = name;
        this.isAI = isAI;
    }

    public String getName() {
        return name;
    }

    public boolean isAI() {
        return isAI;
    }
}
