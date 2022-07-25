package edu.ib.endpoints.controller;

import edu.ib.endpoints.data.dtos.CardDto;
import edu.ib.endpoints.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController("/card")
public class CardController {
    private final CardService cardService;

    @Autowired
    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping
    public CardDto save(@RequestBody CardDto cardDto){
        return cardService.save(cardDto);
    }

    @GetMapping
    public CardDto getById(@RequestParam Long id){
        return cardService.getCard(id);
    }

    @GetMapping("/all")
    public Iterable<CardDto> getAll(){
        return cardService.getCards();
    }
}
