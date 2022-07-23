package edu.ib.splendor.database.entities;

public class NameCheckedPair {
    private final String name;
    private final boolean isChecked;

    public NameCheckedPair(String name, boolean isAI) {
        this.name = name;
        this.isChecked = isAI;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return isChecked;
    }
}
