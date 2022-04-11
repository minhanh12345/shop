package com.example.project_shop.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    @ManyToOne
    private OrderEntity order;
    @ManyToOne
    private VegetableEntity vegetable;
    private Double discount;

    public Double getTotal() {
        return this.vegetable.getPrice()*amount-discount;
    }
}
