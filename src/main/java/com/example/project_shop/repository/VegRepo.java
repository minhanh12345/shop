package com.example.project_shop.repository;

import com.example.project_shop.entity.VegetableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VegRepo extends JpaRepository<VegetableEntity,Long>, JpaSpecificationExecutor<VegetableEntity> {
}
