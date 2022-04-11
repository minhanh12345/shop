package com.example.project_shop.entity;

import com.example.project_shop.enumdata.Status;
import com.example.project_shop.enumdata.TypeVeg;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class VegetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private TypeVeg typeVeg;
    private Double manyGram;
    private String desc;
    private String howToUse;
    private Status available;
    private String picture;
    @ManyToOne
    private SupplierEntity supplier;
}
