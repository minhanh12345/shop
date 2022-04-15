package com.example.project_shop.service;

import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.entity.VegetableEntity;
import com.example.project_shop.util.PagingAndSortingModel;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllWithPagingAndSorting(PagingAndSortingModel model);
    UserEntity save(UserEntity vegetable);
    void delete(Long idUser);
}
