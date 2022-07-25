package edu.ib.endpoints.service;

import edu.ib.endpoints.data.dtos.UserDto;
import edu.ib.endpoints.data.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto save(UserDto userDto){
        return  userRepository.save(userDto);
    }

    public UserDto getUser(Long id){
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Iterable<UserDto> getUsers(){
        return userRepository.findAll();
    }
}
