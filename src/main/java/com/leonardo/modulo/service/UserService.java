package com.leonardo.modulo.service;

import com.leonardo.modulo.dto.UserDto;
import com.leonardo.modulo.entity.User;
import com.leonardo.modulo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto addUser(UserDto userDto){
        User user = new User(userDto);
        user = this.userRepository.save(user);
        return user.userToDto();
    }

    public List<UserDto> getAllUsers(){
        return this.userRepository.findAll().stream()
                .map(u -> u.userToDto())
                .toList();
    }

}
