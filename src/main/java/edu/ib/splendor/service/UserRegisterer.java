package edu.ib.splendor.service;

import edu.ib.splendor.Configuration;
import edu.ib.splendor.database.repositories.dtos.UserDto;
import edu.ib.splendor.database.repositories.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterer {
    UserRepository userRepository;

    @Autowired
    public UserRegisterer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addUser(){userRepository.save(new UserDto("admin", "password"));
    }
}
