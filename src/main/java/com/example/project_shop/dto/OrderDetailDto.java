package com.example.project_shop.dto;

import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class OrderDetailDto {
    private Long vegId;
    private Integer amount;
}
