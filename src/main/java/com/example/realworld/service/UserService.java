package com.example.realworld.service;

import com.example.realworld.dto.UserDto;
import com.example.realworld.dto.UserResDto;
import com.example.realworld.dto.UserUpdateDto;
import com.example.realworld.model.User;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface UserService {
    UserDto login(String email, String password);
    UserResDto register(String username, String email, String password);
    UserResDto update(UUID userID, UserUpdateDto dto);
    User getCurrentUser();
}
