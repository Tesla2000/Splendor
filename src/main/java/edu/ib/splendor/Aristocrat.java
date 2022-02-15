package edu.ib.splendor;

public record Aristocrat(int red, int green, int blue, int brown, int white, int points) {

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getBrown() {
        return brown;
    }

    public int getWhite() {
        return white;
    }

    public int getPoints() {
        return points;
    }
}
