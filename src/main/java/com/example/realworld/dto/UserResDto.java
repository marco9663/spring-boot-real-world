package com.example.realworld.dto;

import lombok.Data;

@Data
public class UserResDto {
    private UserDto user;

    public UserResDto(UserDto user) {
        this.user = user;
    }
}
