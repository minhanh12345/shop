package com.example.project_shop.repository;

import com.example.project_shop.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<CommentEntity,Long>, JpaSpecificationExecutor<CommentEntity> {
    @Query("select c from CommentEntity c where c.parentCommentId = ?1")
    Optional<List<CommentEntity>> getAllByParentComment(Long idCmt);
}
