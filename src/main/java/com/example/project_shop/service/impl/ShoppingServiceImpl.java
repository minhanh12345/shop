package com.example.project_shop.service.impl;

import com.example.project_shop.dto.OrderDetailDto;
import com.example.project_shop.dto.OrderDto;
import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.OderDetailEntity;
import com.example.project_shop.entity.OrderEntity;
import com.example.project_shop.repository.OrderDetailRepo;
import com.example.project_shop.repository.OrderRepo;
import com.example.project_shop.repository.UserRepo;
import com.example.project_shop.repository.VegRepo;
import com.example.project_shop.service.Shopping;
import com.example.project_shop.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class ShoppingServiceImpl implements Shopping {
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
        String dateNow = CommonUtil.convertDateSqlToString(Date.valueOf(LocalDate.now()), "dd/MM/yyyy");
        OrderEntity order = orderRepo.save(new OrderEntity(dateNow, 1L, orderDto.getMessage(), true, orderDto.getTypePay()));
        for (OrderDetailDto odd : orderDto.getLstOrderDetail()
        ) {
            orderDetailRepo.save(new OderDetailEntity(odd.getAmount(), order, vegRepo.getById(odd.getVegId())));
        }

        return null;

    }
}
