package com.example.project_shop.controller;

import com.example.project_shop.dto.OrderDto;
import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {
    @Autowired
    ShoppingService shoppingService;

    @PostMapping("/checkOut")
    public ResponseDto checkOut(@RequestBody OrderDto orderDto) {
        ResponseDto responseDto = shoppingService.checkOut(orderDto);
        return responseDto;
    }

}
