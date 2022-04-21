package com.example.project_shop.service;

import com.example.project_shop.entity.CommentEntity;
import com.example.project_shop.util.PagingAndSortingModel;

import java.util.List;

public interface CommentService {
    List<CommentEntity> getAllWithPagingAndSorting(PagingAndSortingModel model);

    CommentEntity save(CommentEntity comment);

    void delete(Long idCmt);

    List<CommentEntity> getRepComment(Long idCmt);
}
