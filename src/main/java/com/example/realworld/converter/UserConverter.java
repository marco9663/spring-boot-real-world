package com.example.realworld.converter;

import com.example.realworld.dto.UserLoginDto;
import com.example.realworld.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User dtoToEntity(UserLoginDto dto){
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    };

    
}
