package edu.ib.splendor.service;

import edu.ib.splendor.database.entities.Board;
import edu.ib.splendor.database.repositories.AristocratRepository;
import edu.ib.splendor.database.repositories.BoardRepository;
import edu.ib.splendor.database.repositories.CardRepository;
import edu.ib.splendor.database.repositories.PlayerRepository;
import edu.ib.splendor.database.repositories.projections.BoardDto;
import edu.ib.splendor.database.repositories.projections.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GameMapper {
    private final AristocratRepository aristocratRepository;
    private final BoardRepository boardRepository;
    private final CardRepository cardRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public GameMapper(AristocratRepository aristocratRepository, BoardRepository boardRepository, CardRepository cardRepository, PlayerRepository playerRepository) {
        this.aristocratRepository = aristocratRepository;
        this.boardRepository = boardRepository;
        this.cardRepository = cardRepository;
        this.playerRepository = playerRepository;
    }

    public void saveGame(Board board){
        BoardDto boardDto = new BoardDto();
        ArrayList<PlayerDto> playerDtos = new ArrayList<>();
    }
}
