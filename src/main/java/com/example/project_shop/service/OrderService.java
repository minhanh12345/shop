package com.example.project_shop.service;

import com.example.project_shop.entity.OrderDetailEntity;
import com.example.project_shop.entity.OrderEntity;
import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.model.OrderViewAdmin;
import com.example.project_shop.model.OrderViewDetail;
import com.example.project_shop.util.PagingAndSortingModel;

import java.util.List;

public interface OrderService {
    List<OrderViewAdmin> getAllWithPagingAndSorting(PagingAndSortingModel model);
    List<OrderViewDetail> getAllODByOrder(Long idOder);
}
