package com.example.project_shop.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SendMessage {
    private Long byUser;
    private Long idRoom;
    private String content;
}
