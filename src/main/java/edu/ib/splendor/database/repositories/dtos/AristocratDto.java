package edu.ib.splendor.database.repositories.dtos;

import java.time.LocalDateTime;


public class AristocratDto {
    private Long id;
    private int red;
    private int green;
    private int blue;
    private int brown;
    private int white;
    private String image;
    private PlayerDto playerDto;
    private BoardDto boardDto;
    private LocalDateTime creation;

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public BoardDto getBoardDto() {
        return boardDto;
    }

    public AristocratDto(Long id) {
        this.id = id;
    }

    public AristocratDto() {
    }

    public PlayerDto getPlayerDto() {
        return playerDto;
    }

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPlayerDto(PlayerDto playerDto) {
        this.playerDto = playerDto;
    }

    public void setBoardDto(BoardDto boardDto) {
        this.boardDto = boardDto;
    }
}
