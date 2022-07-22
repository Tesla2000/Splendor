package edu.ib.splendor.database.repositories.projections;

import javax.persistence.*;

@Entity
public class BoardDto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private int red;
    private int green;
    private int blue;
    private int brown;
    private int white;
    private int gold;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getBrown() {
        return brown;
    }

    public void setBrown(int brown) {
        this.brown = brown;
    }

    public int getWhite() {
        return white;
    }

    public void setWhite(int white) {
        this.white = white;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
