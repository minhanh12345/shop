package com.example.project_shop.service;

import com.example.project_shop.dto.OrderDto;
import com.example.project_shop.dto.ResponseDto;

public interface ShoppingService {
    ResponseDto checkOut(OrderDto orderDto);
    ResponseDto pushShipperToOrder(Long idShipper,Long idOrder);
}
