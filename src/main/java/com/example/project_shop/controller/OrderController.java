package com.example.project_shop.controller;

import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.OrderEntity;
import com.example.project_shop.model.OrderViewAdmin;
import com.example.project_shop.model.OrderViewDetail;
import com.example.project_shop.service.OrderService;
import com.example.project_shop.util.Constant;
import com.example.project_shop.util.PagingAndSortingModel;
import com.example.project_shop.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/findAll")
    public ResponseDto<List<OrderViewAdmin>> getAllOrder(
            @RequestParam(defaultValue = "", required = false) String filterIdOrder,
            @RequestParam(defaultValue = "", required = false) String filterStatusOrder,
            @RequestParam(defaultValue = "", required = false) String filterTypePayOrder,
            @RequestParam(defaultValue = "0", required = false) int pageIndex,
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(required = false, defaultValue = "") String sortColumn,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        SearchCriteria searchCriteria = new SearchCriteria(filterIdOrder, filterStatusOrder, filterTypePayOrder);
        ResponseDto<List<OrderViewAdmin>> responseDto = new ResponseDto<>();
        responseDto.setContent(orderService.getAllWithPagingAndSorting(new PagingAndSortingModel(searchCriteria, pageIndex, pageSize, sortColumn, sortDirection)));
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }
    @GetMapping("/getODByOrder/{idOrder}")
    public ResponseDto<List<OrderViewDetail>> getODByOrder(Long idOrder){
        ResponseDto<List<OrderViewDetail>> responseDto = new ResponseDto<>();
        responseDto.setContent(orderService.getAllODByOrder(idOrder));
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }
}
