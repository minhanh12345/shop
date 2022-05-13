package com.example.project_shop.dto;
import com.example.project_shop.exception.ProductException;
import com.example.project_shop.util.Constant;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
public class ResponseDto<T> implements Serializable {
    private static  final long serialVersionUID = -3931471505590865499L;

    private String statusCode;
    private String message;
    private T content;

    public ResponseDto() {
    }

    public ResponseDto(String statusCode, T content) {
        this.statusCode = statusCode;
        this.content = content;
    }

    public ResponseDto(String statusCode, T content, String msg) {
        this.statusCode = statusCode;
        this.content = content;
        this.message = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString(){
        return "ResponseDto [content=" + content + ", status=" + statusCode + "]";
    }

    public ResponseDto<T> setResponseResponse(ResponseDto<T> responseDto, String errorMessage, String statusCode, T content) {
        if (content == null) {
            responseDto.setContent(null);
            responseDto.setStatusCode(statusCode);
            return responseDto;
        }
        responseDto.setContent(content);
        responseDto.setStatusCode(Constant.ErrorType.SUCCESS);
        return responseDto;
    }

    public ResponseDto<T> setResponseResponse(String statusCode, T content) {
        ResponseDto<T> responseDto = new ResponseDto<>();
        if (content == null) {
            responseDto.setContent(null);
            responseDto.setStatusCode(statusCode);
            return responseDto;
        }
        responseDto.setContent(content);
        responseDto.setStatusCode(Constant.ErrorType.SUCCESS);
        return responseDto;
    }

    public ResponseDto<T> responseDtoError(Exception e, ResponseDto<T> responseDto) {
        responseDto.setContent(null);
        responseDto.setStatusCode("500");
        if (e instanceof ProductException) {
            return responseDto;
        }
        return responseDto;
    }

}
