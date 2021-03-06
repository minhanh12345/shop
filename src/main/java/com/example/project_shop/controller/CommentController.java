package com.example.project_shop.controller;

import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.entity.CommentEntity;
import com.example.project_shop.service.CommentService;
import com.example.project_shop.util.Constant;
import com.example.project_shop.util.PagingAndSortingModel;
import com.example.project_shop.util.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cmt")
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("/findAll")
    public ResponseDto<List<CommentEntity>> getAll(
            @RequestParam(defaultValue = "", required = false) Long filterIdBlog,
            @RequestParam(defaultValue = "0", required = false) int pageIndex,
            @RequestParam(defaultValue = "10", required = false) int pageSize,
            @RequestParam(required = false, defaultValue = "") String sortColumn,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        SearchCriteria searchCriteria = new SearchCriteria(filterIdBlog);
        ResponseDto<List<CommentEntity>> responseDto = new ResponseDto<>();
        responseDto.setContent(commentService.getAllWithPagingAndSorting(new PagingAndSortingModel(searchCriteria, pageIndex, pageSize, sortColumn, sortDirection)));
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }

    @PostMapping("/save")
    public ResponseDto<CommentEntity> save(@RequestBody CommentEntity comment) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContent(commentService.save(comment));
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        commentService.delete(id);
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }

    @GetMapping("/getAllCmtByParent/{id}")
    public ResponseDto getAllCmtByParent(@PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        List<CommentEntity> list = commentService.getRepComment(id);
        responseDto.setContent(list);
        responseDto.setMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.CodeRes.SUCCESS);
        return responseDto;
    }

}
