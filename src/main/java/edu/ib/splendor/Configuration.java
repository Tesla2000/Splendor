package edu.ib.splendor;

import edu.ib.splendor.service.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.context.annotation.Configuration
public class Configuration {
    public static GameMapper gameMapper;

    @Autowired
    public Configuration(GameMapper gameMapper) {
        Configuration.gameMapper = gameMapper;
    }
}
