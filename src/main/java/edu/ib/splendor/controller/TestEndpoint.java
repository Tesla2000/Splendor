package edu.ib.splendor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestEndpoint {

    @GetMapping("/example")
    public String hello(){
        return "It is alive!";
    }

}
