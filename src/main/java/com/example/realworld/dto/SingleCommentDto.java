package com.example.realworld.dto;

import com.example.realworld.model.Profile;
import lombok.Data;

import java.util.Date;

@Data
public class SingleCommentDto {
    Integer id;
    Date createdAt;
    Date updatedAt;
    String body;
    Profile author;
}
