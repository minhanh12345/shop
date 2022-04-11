package com.example.project_shop.dto;


import com.example.project_shop.exception.ProductException;
import com.example.project_shop.util.Constant;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class ResponseDto<T> implements Serializable {
    private static final long serialVersionUID = -3931471505590865499L;
    private int statusCode;
    private T content;
    private String errorType;
    private String errorMessage;

    public ResponseDto() {
    }

    public ResponseDto(int statusCode, T content, String errorType, String errorMessage) {
        this.statusCode = statusCode;
        this.content = content;
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        return "ResponseDto [content=" + content + ", message=" + errorMessage + ", status=" + statusCode + "]";
    }

    public ResponseDto<T> setResponseResponse(ResponseDto<T> responseDto, String errorMessage, int statusCode, T content) {
        if (content == null) {
            responseDto.setContent(null);
            responseDto.setErrorMessage(errorMessage);
            responseDto.setStatusCode(statusCode);
            return responseDto;
        }
        responseDto.setContent(content);
        responseDto.setErrorMessage(Constant.Message.SUCCESS);
        responseDto.setStatusCode(Constant.Code.SUCCESS);
        return responseDto;
    }

    public ResponseDto<T> responseDtoError(Exception e, ResponseDto<T> responseDto) {
        responseDto.setContent(null);
        responseDto.setStatusCode(500);
        if (e instanceof ProductException) {
            responseDto.setErrorMessage(((ProductException) e).getErrorResponse().getMessageEN());
            responseDto.setErrorType(((ProductException) e).getErrorResponse().getErrorType());
            return responseDto;
        }
        responseDto.setErrorMessage(e.getMessage());
        responseDto.setErrorType(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return responseDto;
    }
}
