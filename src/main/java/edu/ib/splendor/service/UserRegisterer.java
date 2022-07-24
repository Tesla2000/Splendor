package edu.ib.splendor.service;

import edu.ib.splendor.Configuration;
import edu.ib.splendor.database.repositories.dtos.GameDto;
import edu.ib.splendor.database.repositories.dtos.UserDto;
import edu.ib.splendor.database.repositories.dtos.WaitDto;
import edu.ib.splendor.database.repositories.interfaces.GameRepository;
import edu.ib.splendor.database.repositories.interfaces.UserRepository;
import edu.ib.splendor.database.repositories.interfaces.WaitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterer {
    UserRepository userRepository;
    GameRepository gameRepository;
    WaitRepository waitRepository;

    @Autowired
    public UserRegisterer(UserRepository userRepository, GameRepository gameRepository, WaitRepository waitRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
        this.waitRepository = waitRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addUser(){userRepository.save(new UserDto("admin", "password"));
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void createHostedGame(){
//        userRepository.save(new UserDto("admin", "password"));
//        GameDto gameDto = new GameDto();
//        gameDto.setStarted(false);
//        gameRepository.save(gameDto);
//        WaitDto waitDto = new WaitDto(gameDto);
//        waitDto.setReady(true);
//        waitDto.setPlayerName("ReadyPlayer");
//        waitRepository.save(waitDto);
//        waitDto = new WaitDto(gameDto);
//        waitDto.setGameKey("key");
//        waitDto.setReady(false);
//        waitRepository.save(waitDto);
//    }
}
