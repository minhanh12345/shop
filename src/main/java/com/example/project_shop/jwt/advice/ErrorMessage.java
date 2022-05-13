package com.example.project_shop.jwt.advice;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {
    private int httpStatus;
    private Date date;
    private String message;
    private String description;

    public ErrorMessage(int httpStatus, Date date, String message, String description) {
        this.httpStatus = httpStatus;
        this.date = date;
        this.message = message;
        this.description = description;
    }
}
