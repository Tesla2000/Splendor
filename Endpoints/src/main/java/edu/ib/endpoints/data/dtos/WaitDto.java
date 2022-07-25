package edu.ib.endpoints.data.dtos;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class WaitDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String gameKey;
    private String playerName;
    private Boolean ready;
    @ManyToOne
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
}
