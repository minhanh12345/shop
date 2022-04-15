package com.example.project_shop.model;

import lombok.Data;

@Data
public class OrderViewAdmin {
    private Long idOrder;
    private String nameCustomer;
    private String orderDate;
    private String shipDate;
    private Long status;
    private String typePay;
    private Float totalPrice;
}
