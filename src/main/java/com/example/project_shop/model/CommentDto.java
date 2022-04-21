package com.example.project_shop.model;

import lombok.Data;

import java.util.List;

@Data
public class CommentDto {
    private String nameUser;
    private String content;
    private List<CommentDto> commentRep;
}
