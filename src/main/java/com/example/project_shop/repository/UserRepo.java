package com.example.project_shop.repository;

import com.example.project_shop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends JpaRepository<UserEntity,Long>, JpaSpecificationExecutor<UserEntity> {
}
