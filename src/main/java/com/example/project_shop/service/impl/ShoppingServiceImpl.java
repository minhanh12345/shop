package com.example.project_shop.service.impl;

import com.example.project_shop.dto.OrderDetailDto;
import com.example.project_shop.dto.OrderDto;
import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.OderDetailEntity;
import com.example.project_shop.entity.OrderEntity;
import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.repository.OrderDetailRepo;
import com.example.project_shop.repository.OrderRepo;
import com.example.project_shop.repository.UserRepo;
import com.example.project_shop.repository.VegRepo;
import com.example.project_shop.service.ShoppingService;
import com.example.project_shop.util.CommonUtil;
import com.example.project_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    @Autowired
    OrderDetailRepo orderDetailRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    VegRepo vegRepo;


    @Override
    public ResponseDto checkOut(OrderDto orderDto) {
        ResponseDto responseDto = new ResponseDto<>();
        UserEntity customer;
        if (orderDto.getCustomerId() != null) {
            customer = userRepo.getById(orderDto.getCustomerId());
        } else {
            customer = userRepo.save(new UserEntity(orderDto.getName(), orderDto.getPhone(), orderDto.getAddress(), Constant.Role.CUSTOMER));
        }
        OrderEntity order = orderRepo.save(new OrderEntity(customer, CommonUtil.timeNowToString(), 1L, orderDto.getMessage(), 1L, orderDto.getTypePay()));
        for (OrderDetailDto odd : orderDto.getLstOrderDetail()
        ) {
            orderDetailRepo.save(new OderDetailEntity(odd.getAmount(), order, vegRepo.getById(odd.getVegId())));
        }
        responseDto.setContent(order);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }

    @Override
    public ResponseDto pushShipperToOrder(Long idShipper, Long idOrder) {
        ResponseDto responseDto = new ResponseDto<>();
        OrderEntity order = orderRepo.getById(idOrder);
        order.setShipper(userRepo.getById(idShipper));
        order.setShipDate(CommonUtil.timeNowToString());
        order.setPriceShip(15000F);
        OrderEntity orderSave = orderRepo.save(order);
        responseDto.setContent(orderSave);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }
    public ResponseDto measureDistance (){

    }

}
