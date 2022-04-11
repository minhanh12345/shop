package com.example.project_shop.repository;

import com.example.project_shop.entity.OderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDetailRepo extends JpaRepository<OderDetailEntity,Long>, JpaSpecificationExecutor<OderDetailEntity> {
}
