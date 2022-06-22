package com.example.realworld.dto;

import com.example.realworld.model.User;
import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String token;
    private String username;
    private String bio;
    private String image;

    public UserDto(String email, String token, String username, String bio, String image) {
        this.email = email;
        this.token = token;
        this.username = username;
        this.bio = bio;
        this.image = image;
    }

    public UserDto(User user, String token){
        this.bio = user.getProfile().getBio();
        this.email = user.getEmail();
        this.username = user.getProfile().getUsername();
        this.image = user.getProfile().getImage();
        this.token = token;
    }
}
