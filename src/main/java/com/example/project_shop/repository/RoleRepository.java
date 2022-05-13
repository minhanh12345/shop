package com.example.project_shop.repository;

import com.example.project_shop.entity.RoleEntity;
import com.example.project_shop.enumdata.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(ERole name);
}
