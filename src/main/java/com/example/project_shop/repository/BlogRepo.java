package com.example.project_shop.repository;

import com.example.project_shop.entity.BlogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BlogRepo extends JpaRepository<BlogEntity,Long> , JpaSpecificationExecutor<BlogEntity> {
}
