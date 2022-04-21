package com.example.project_shop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    private UserEntity createUser;
    @ManyToOne
    private BlogEntity blog;
    private Long parentCommentId;
    private String createTime;
}
