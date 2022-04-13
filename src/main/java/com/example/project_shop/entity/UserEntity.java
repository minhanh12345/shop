package com.example.project_shop.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "role")
    private String role;

    public UserEntity() {
    }



    public UserEntity(String fullName, String phone, String address, String role) {
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.role = role;
    }
}
