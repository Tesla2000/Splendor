package edu.ib.splendor;

import edu.ib.splendor.database.repositories.dtos.WaitDto;
import edu.ib.splendor.database.repositories.interfaces.GameRepository;
import edu.ib.splendor.database.repositories.interfaces.UserRepository;
import edu.ib.splendor.database.repositories.interfaces.WaitRepository;
import edu.ib.splendor.service.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.context.annotation.Configuration
public class Configuration {
    public static GameMapper gameMapper;
    public static UserRepository userRepository;
    public static WaitRepository waitRepository;
    public static GameRepository gameRepository;

    @Autowired
    public Configuration(GameMapper gameMapper, UserRepository userRepository, WaitRepository waitRepository, GameRepository gameRepository) {
        Configuration.gameMapper = gameMapper;
        Configuration.userRepository = userRepository;
        Configuration.gameRepository = gameRepository;
        Configuration.waitRepository = waitRepository;
    }
}
