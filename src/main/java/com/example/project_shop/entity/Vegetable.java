package com.example.project_shop.entity;

import com.example.project_shop.enumdata.TypeVeg;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
@Entity
public class Vegetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private TypeVeg typeVeg;
    private Double manyGram;
    private String desc;
    private String howToUse;
    private Boolean available;
    private String picture;
    private String supplier;
}
