package com.example.realworld.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MultipleArticleDto {

    ArrayList<SingleArticleDto> articles;
    Integer articlesCount;
}
