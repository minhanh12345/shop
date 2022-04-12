package com.example.project_shop.enumdata;
import lombok.Getter;


@Getter
public enum Status {
    AVAILABLE("AVAILABLE"),
    OUTSTOCK("OUTSTOCK");
   private String status;

    Status(String status) {
        this.status = status;
    }
}
