package edu.ib.splendor;

import edu.ib.splendor.database.repositories.access.GameRepository;
import edu.ib.splendor.database.repositories.access.UserRepository;
import edu.ib.splendor.database.repositories.access.WaitRepository;
import edu.ib.splendor.service.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Configuration {
    public static GameMapper gameMapper;
    public static UserRepository userRepository;
    public static WaitRepository waitRepository;
    public static GameRepository gameRepository;
    public static List<String> playerNames;
    public static String URL = "";

    public Configuration(GameMapper gameMapper, UserRepository userRepository, WaitRepository waitRepository, GameRepository gameRepository) {
        Configuration.gameMapper = gameMapper;
        Configuration.userRepository = userRepository;
        Configuration.gameRepository = gameRepository;
        Configuration.waitRepository = waitRepository;
    }
}
