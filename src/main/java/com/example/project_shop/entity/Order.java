package com.example.project_shop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Customer customer;
    private String orderDate;
    private String shipDate;
    @OneToOne
    private Shipper shipper;
    private Long transStatus;
    private String messsage;
    private Boolean delected;
    private Double priceShip;
}
