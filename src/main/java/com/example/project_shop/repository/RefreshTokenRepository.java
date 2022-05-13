package com.example.project_shop.repository;

import com.example.project_shop.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    @Override
    Optional<RefreshTokenEntity> findById(Long id);
    Optional<RefreshTokenEntity> findByToken(String token);
}
