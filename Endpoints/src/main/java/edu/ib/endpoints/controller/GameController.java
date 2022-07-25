package edu.ib.endpoints.controller;

import edu.ib.endpoints.data.dtos.GameDto;
import edu.ib.endpoints.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/game")
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public GameDto save(@RequestBody GameDto gameDto){
        return gameService.save(gameDto);
    }

    @GetMapping("/all")
    public Iterable<GameDto> getAll(){
        return gameService.getGames();
    }

    @GetMapping
    public GameDto getById(@RequestParam Long id){
        return gameService.getGame(id);
    }
}
