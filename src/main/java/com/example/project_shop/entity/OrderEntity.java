package com.example.project_shop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private UserEntity customer;
    @Column(name = "order_date")
    private String orderDate;
    @Column(name = "ship_date")
    private String shipDate;
    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private UserEntity shipper;
    @Column(name = "tran_status")
    private Long transStatus;
    @Column(name = "message")
    private String messsage;
    @Column(name = "delected")
    private Boolean delected;
    @Column(name = "price_ship")
    private Float priceShip;
}
