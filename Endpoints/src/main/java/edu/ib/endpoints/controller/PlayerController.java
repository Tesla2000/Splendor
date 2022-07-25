package edu.ib.endpoints.controller;

import edu.ib.endpoints.data.dtos.PlayerDto;
import edu.ib.endpoints.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/player")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public PlayerDto save(@RequestBody PlayerDto playerDto){
        return playerService.save(playerDto);
    }

    @GetMapping
    public PlayerDto getById(@RequestParam Long id){
        return playerService.getPlayer(id);
    }

    @GetMapping("/all")
    public Iterable<PlayerDto> getAll(){
        return playerService.getPlayers();
    }
}
