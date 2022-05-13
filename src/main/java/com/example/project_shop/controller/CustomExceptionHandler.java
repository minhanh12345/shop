package com.example.project_shop.controller;

import com.example.project_shop.dto.ResponseDto;
import com.example.project_shop.exception.ErrorResponse;
import com.example.project_shop.exception.ProductException;
import com.example.project_shop.util.Constant;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Log4j2
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({InvalidFormatException.class})
    public ResponseDto IdFomatException(InvalidFormatException e) {

        String[] messageE = e.getMessage().split("\"");
        String message = "";
        if(messageE.length == 5){
            message = messageE[3] + " không đúng định dạng";
        }else{
            message = e.getMessage();
        }

        return new ResponseDto<>(
                Constant.CodeRes.BAD_REQUEST,
                message);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto IdFomatException(MethodArgumentTypeMismatchException e) {

        String[] messageE = e.getMessage().split("\"");
        String message = "";
        if(messageE.length == 5){
            message = messageE[3] + " không đúng định dạng";
        }else{
            message = "truyền không đúng định dạng";
        }

        return new ResponseDto<>(
                Constant.CodeRes.BAD_REQUEST,
                message);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = "";
        for (ObjectError oe : e.getAllErrors()) {
            message += oe.getDefaultMessage() + ",";
        }

        return new ResponseDto<String>(
                Constant.CodeRes.BAD_REQUEST,
                "Fail",
                message);
    }
    @ExceptionHandler({AuthenticationException.class})
    @ResponseBody
    public ErrorResponse handleMethodUnauthorizedException(AuthenticationException e) {
       return new ErrorResponse("UnAuthorized", e.getMessage(),401);
    }
    @ExceptionHandler(ProductException.class)
    @ResponseBody
    protected ErrorResponse returnException(ProductException ex) {
        return ex.getErrorResponse();
    }
}
