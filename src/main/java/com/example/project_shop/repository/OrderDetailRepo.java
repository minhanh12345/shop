package com.example.project_shop.repository;

import com.example.project_shop.entity.OrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetailEntity,Long>, JpaSpecificationExecutor<OrderDetailEntity> {
    @Query("select o from OrderDetailEntity o where o.order.id = ?1")
    List<OrderDetailEntity> getAllByOrder(Long idOrder);
}
