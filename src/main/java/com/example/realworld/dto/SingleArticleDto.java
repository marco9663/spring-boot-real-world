package com.example.realworld.dto;

import com.example.realworld.model.Profile;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class SingleArticleDto {
    String slug;
    String title;
    String description;
    String body;
    ArrayList<String> tagList;
    Date createdAt;
    Date updatedAt;
    Boolean favorited;
    Integer favoritesCount;
    Profile author;
}
