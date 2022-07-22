package edu.ib.splendor;

import edu.ib.splendor.service.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mapper {
    public static GameMapper gameMapper;

    @Autowired
    public Mapper(GameMapper gameMapper) {
        Mapper.gameMapper = gameMapper;
    }

    public static GameMapper getGameMapper(){
        return gameMapper;
    }
}
