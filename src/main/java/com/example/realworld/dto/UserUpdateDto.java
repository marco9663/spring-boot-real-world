package com.example.realworld.dto;

import lombok.Data;

@Data
public class UserUpdateDto {
    private String email;
    private String username;
    private String password;
    private String image;
    private String bio;
}
