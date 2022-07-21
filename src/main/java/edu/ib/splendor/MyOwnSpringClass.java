package edu.ib.splendor;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MyOwnSpringClass {
    public static void main(String[] args) {
        SpringApplication.run(MyOwnSpringClass.class, args);
        Application.launch(GameApplication.class, args);
    }
}
