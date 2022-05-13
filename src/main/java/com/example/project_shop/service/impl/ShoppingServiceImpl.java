package com.example.project_shop.service.impl;

import com.example.project_shop.dto.OrderDetailDto;
import com.example.project_shop.dto.OrderDto;
import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.OrderDetailEntity;
import com.example.project_shop.entity.OrderEntity;
import com.example.project_shop.entity.RoleEntity;
import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.enumdata.ERole;
import com.example.project_shop.repository.*;
import com.example.project_shop.service.ShoppingService;
import com.example.project_shop.util.CommonUtil;
import com.example.project_shop.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
@Autowired
    RoleRepository roleRepository;

    @Override
    public ResponseDto<OrderEntity> checkOut(OrderDto orderDto) {
        ResponseDto<OrderEntity> responseDto = new ResponseDto<>();
        UserEntity customer;
        if (orderDto.getCustomerId() != null) {
            customer = userRepo.getById(orderDto.getCustomerId());
        } else {
            Set<RoleEntity> roles = new HashSet<>();
            Optional<RoleEntity> userRole = roleRepository.findByName(ERole.ROLE_USER);
            roles.add(userRole.get());
//            customer = userRepo.save(new UserEntity(orderDto.getName(), orderDto.getPhone(), orderDto.getAddress(), roles));
            customer= userRepo.getById(1L);
        }
        OrderEntity order = orderRepo.save(new OrderEntity(customer, CommonUtil.timeNowToString(), 1L, orderDto.getMessage(), 1L, orderDto.getTypePay()));
        for (OrderDetailDto odd : orderDto.getLstOrderDetail()
        ) {
            orderDetailRepo.save(new OrderDetailEntity(odd.getAmount(), order, vegRepo.getById(odd.getVegId())));
        }
        responseDto.setContent(order);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
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
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }


}
