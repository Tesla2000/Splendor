package edu.ib.endpoints.service;

import edu.ib.endpoints.data.dtos.CardDto;
import edu.ib.endpoints.data.interfaces.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Iterable<CardDto> getCards(){
        return cardRepository.findAll();
    }

    public CardDto getCard(Long id){
        return cardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public CardDto save(CardDto cardDto){
        return cardRepository.save(cardDto);
    }
}
