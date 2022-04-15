package com.example.project_shop.controller;

import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.BlogEntity;
import com.example.project_shop.entity.UserEntity;
import com.example.project_shop.service.BlogService;
import com.example.project_shop.util.Constant;
import com.example.project_shop.util.PagingAndSortingModel;
import com.example.project_shop.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @GetMapping("/findAll")
    public ResponseDto<List<BlogEntity>> getAll(
            @RequestParam(defaultValue = "", required = false) String filterCategoryBlog,
            @RequestParam(defaultValue = "0", required = false) int pageIndex,
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(required = false, defaultValue = "") String sortColumn,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        SearchCriteria searchCriteria = new SearchCriteria(filterCategoryBlog);
        ResponseDto<List<BlogEntity>> responseDto = new ResponseDto<>();
        responseDto.setContent(blogService.getAllWithPagingAndSorting(new PagingAndSortingModel(searchCriteria, pageIndex, pageSize, sortColumn, sortDirection)));
        responseDto.setErrorMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }
    @PostMapping("/save")
    public ResponseDto<BlogEntity> save(@RequestBody BlogEntity user){
        ResponseDto responseDto=new ResponseDto();
        responseDto.setContent(blogService.save(user));
        responseDto.setErrorMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id){
        ResponseDto responseDto=new ResponseDto();
        blogService.delete(id);
        responseDto.setErrorMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }
}
