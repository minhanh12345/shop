package com.example.project_shop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="amount")
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name="veg_id")
    private VegetableEntity vegetable;
    @Column(name="discount")
    private Float discount;

    public OrderDetailEntity() {
    }

    public OrderDetailEntity(Integer amount, OrderEntity order, VegetableEntity vegetable) {
        this.amount = amount;
        this.order = order;
        this.vegetable = vegetable;
    }

    public Float getTotal() {
        return this.vegetable.getPrice()*amount-discount;
    }
}
