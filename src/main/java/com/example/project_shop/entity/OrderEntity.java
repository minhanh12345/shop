package com.example.project_shop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private UserEntity customer;
    private String orderDate;
    private String shipDate;
    @ManyToOne
    private ShipperEntity shipper;
    private Long transStatus;
    private String messsage;
    private Boolean delected;
    private Double priceShip;
}
