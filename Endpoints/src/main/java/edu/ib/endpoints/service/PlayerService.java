package edu.ib.endpoints.service;

import edu.ib.endpoints.data.dtos.PlayerDto;
import edu.ib.endpoints.data.interfaces.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerDto save(PlayerDto playerDto){
        return playerRepository.save(playerDto);
    }

    public PlayerDto getPlayer(Long id){
        return playerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Iterable<PlayerDto> getPlayers(){
        return playerRepository.findAll();
    }
}
