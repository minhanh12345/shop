package com.example.project_shop.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    private List<OrderDetailDto> lstOrderDetail;
    private String name;
    private String phone;
    private String address;
    private String message;
    private Long customerId;
    private String typePay;

}
