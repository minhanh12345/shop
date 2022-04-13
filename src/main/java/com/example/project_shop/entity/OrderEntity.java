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
    private Long delected;
    @Column(name = "price_ship")
    private Float priceShip;
    @Column(name="type_pay")
    private String typePay;

    public OrderEntity( UserEntity customer,String orderDate,   Long transStatus, String messsage, Long delected,String typePay) {
        this.customer=customer;
        this.orderDate = orderDate;
        this.transStatus = transStatus;
        this.messsage = messsage;
        this.delected = delected;
        this.typePay=typePay;
    }

    public OrderEntity() {

    }
}
