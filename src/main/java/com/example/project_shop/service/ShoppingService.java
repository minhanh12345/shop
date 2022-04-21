package com.example.project_shop.service;

import com.example.project_shop.dto.OrderDto;
import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.OrderEntity;

public interface ShoppingService {
    ResponseDto<OrderEntity> checkOut(OrderDto orderDto);
    ResponseDto pushShipperToOrder(Long idShipper,Long idOrder);
}
