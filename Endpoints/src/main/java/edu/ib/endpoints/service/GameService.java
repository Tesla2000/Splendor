package edu.ib.endpoints.service;

import edu.ib.endpoints.data.dtos.GameDto;
import edu.ib.endpoints.data.interfaces.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public GameDto save(GameDto gameDto){
        return gameRepository.save(gameDto);
    }

    public Iterable<GameDto> getGames(){
        return gameRepository.findAll();
    }

    public GameDto getGame(Long id){
        return gameRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
