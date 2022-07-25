package edu.ib.endpoints.controller;

import edu.ib.endpoints.data.dtos.UserDto;
import edu.ib.endpoints.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @GetMapping("/all")
    public Iterable<UserDto> getAll(){
        return userService.getUsers();
    }

    @GetMapping
    public UserDto getById(@RequestParam Long id){
        return userService.getUser(id);
    }
}
