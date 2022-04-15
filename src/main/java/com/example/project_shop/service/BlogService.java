package com.example.project_shop.service;

import com.example.project_shop.entity.BlogEntity;
import com.example.project_shop.util.PagingAndSortingModel;

import java.util.List;

public interface BlogService {
    List<BlogEntity> getAllWithPagingAndSorting(PagingAndSortingModel model);
    BlogEntity save(BlogEntity blog);
    void delete(Long idBlog);
}
