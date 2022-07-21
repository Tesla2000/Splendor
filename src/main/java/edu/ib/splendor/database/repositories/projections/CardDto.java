package edu.ib.splendor.database.repositories.projections;

import edu.ib.splendor.database.entities.Tier;

import javax.persistence.*;

@Entity
public class CardDto {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private Boolean visible;
    private Boolean reserve;
    private Tier tier;
    private int red;
    private int green;
    private int blue;
    private int brown;
    private int white;
    private int points;
    private String picture;
    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardDto board;
    @ManyToOne
    @JoinColumn(name = "player_projection_id")
    private PlayerDto playerDto;


    public PlayerDto getPlayerProjection() {
        return playerDto;
    }


    public BoardDto getBoard() {
        return board;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getReserve() {
        return reserve;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setBoard(BoardDto board) {
        this.board = board;
    }

    public void setPlayerProjection(PlayerDto playerDto) {
        this.playerDto = playerDto;
    }
}
