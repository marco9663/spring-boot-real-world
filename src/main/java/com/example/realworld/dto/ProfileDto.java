package com.example.realworld.dto;

import lombok.Data;

@Data
public class ProfileDto {
    String username;
    String bio;
    String image;
    Boolean following;
}
