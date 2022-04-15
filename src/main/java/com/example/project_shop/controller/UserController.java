package com.example.project_shop.controller;

import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.service.UserService;
import com.example.project_shop.util.Constant;
import com.example.project_shop.util.PagingAndSortingModel;
import com.example.project_shop.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public ResponseDto<List<UserEntity>> getAllUser(
            @RequestParam(defaultValue = "", required = false) String filterRoleUser,
            @RequestParam(defaultValue = "", required = false) String filterPhoneUser,
            @RequestParam(defaultValue = "", required = false) String filterNameUser,
            @RequestParam(defaultValue = "", required = false) String filterUsername,
            @RequestParam(defaultValue = "0", required = false) int pageIndex,
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(required = false, defaultValue = "") String sortColumn,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        SearchCriteria searchCriteria = new SearchCriteria(filterRoleUser, filterPhoneUser, filterNameUser, filterUsername);
        ResponseDto<List<UserEntity>> responseDto = new ResponseDto<>();
        responseDto.setContent(userService.getAllWithPagingAndSorting(new PagingAndSortingModel(searchCriteria, pageIndex, pageSize, sortColumn, sortDirection)));
        responseDto.setErrorMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }
    @PostMapping("/save")
    public ResponseDto<UserEntity> save(@RequestBody UserEntity user){
        ResponseDto responseDto=new ResponseDto();
        responseDto.setContent(userService.save(user));
        responseDto.setErrorMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id){
        ResponseDto responseDto=new ResponseDto();
        userService.delete(id);
        responseDto.setErrorMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }
}
