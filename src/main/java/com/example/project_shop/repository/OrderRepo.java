package com.example.project_shop.repository;

import com.example.project_shop.entity.OrderDetailEntity;
import com.example.project_shop.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepo extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

}
