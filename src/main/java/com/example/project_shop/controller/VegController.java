package com.example.project_shop.controller;

import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.VegetableEntity;
import com.example.project_shop.enumdata.Status;
import com.example.project_shop.service.VegService;
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
@RequestMapping("/veg")
public class VegController {
    @Autowired
    VegService vegService;

    @GetMapping("/findAll")
    public ResponseDto<List<VegetableEntity>> getAllVeg(
            @RequestParam(defaultValue = "", required = false) String filterVegType,
            @RequestParam(defaultValue = "", required = false) String filterNameVeg,
            @RequestParam(defaultValue = "", required = false) String filterVegStatus,
            @RequestParam(defaultValue = "", required = false) String filterVegDiscount,
            @RequestParam(defaultValue = "", required = false) String filterVegSupplier,
            @RequestParam(defaultValue = "0", required = false) int pageIndex,
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(required = false, defaultValue = "") String sortColumn,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        SearchCriteria searchCriteria = new SearchCriteria(filterVegType, filterNameVeg, filterVegStatus, filterVegDiscount, filterVegSupplier);
        ResponseDto<List<VegetableEntity>> responseDto = new ResponseDto<>();
        responseDto.setContent(vegService.getAllWithPagingAndSorting(new PagingAndSortingModel(searchCriteria, pageIndex, pageSize, sortColumn, sortDirection)));
        responseDto.setErrorMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }
}
