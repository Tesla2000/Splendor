package edu.ib.splendor.database.repositories.dtos;


public class WaitDto {
    private Long id;

    private String gameKey;
    private String playerName;
    private Boolean ready;
    private GameDto gameDto;

    public WaitDto() {
    }

    public WaitDto(GameDto gameDto) {
        this.gameDto = gameDto;
    }

    public GameDto getGameDto() {
        return gameDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGameKey() {
        return gameKey;
    }

    public void setGameKey(String gameKey) {
        this.gameKey = gameKey;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Boolean getReady() {
        return ready;
    }

    public void setReady(Boolean ready) {
        this.ready = ready;
    }

    public void setGameDto(GameDto gameDto) {
        this.gameDto = gameDto;
    }

    @Override
    public String toString() {
        return "WaitDto{" +
                "id=" + id +
                ", gameKey='" + gameKey + '\'' +
                ", playerName='" + playerName + '\'' +
                ", ready=" + ready +
                ", gameDto=" + gameDto +
                '}';
    }
}
