package edu.ib.splendor.database.repositories.projections;

import javax.persistence.*;

@Entity
public class PlayerDto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer points;
    private String name;
    private int queuePosition;
    private int red;
    private int green;
    private int blue;
    private int brown;
    private int white;
    private int gold;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardDto board;

    public BoardDto getBoard() {
        return board;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQueuePosition() {
        return queuePosition;
    }

    public void setQueuePosition(int queuePosition) {
        this.queuePosition = queuePosition;
    }

    public void setBoard(BoardDto board) {
        this.board = board;
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
