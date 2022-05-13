package com.example.project_shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "refreshtoken")
@Data
public class RefreshTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
    @Column(nullable = false, unique = true)
    private String token;
    @Column(nullable = false)
    private Instant expiryDate;
}
