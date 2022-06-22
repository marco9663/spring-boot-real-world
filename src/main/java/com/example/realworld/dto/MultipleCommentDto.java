package com.example.realworld.dto;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MultipleCommentDto {
    ArrayList<SingleCommentDto> comments;
}
