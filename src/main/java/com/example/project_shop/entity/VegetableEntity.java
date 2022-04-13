package com.example.project_shop.entity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "vegs")
public class VegetableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Float price;
    @Column(name = "type_veg")
    private String typeVeg;
    @Column(name = "many_gram")
    private Float manyGram;
    @Column(name = "desc")
    private String desc;
    @Column(name = "how_to_use")
    private String howToUse;
    @Column(name = "available")
    private String available;
    @Column(name = "picture")
    private String picture;
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
}
