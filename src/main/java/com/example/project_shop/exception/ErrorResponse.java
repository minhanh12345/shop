package com.example.project_shop.exception;

import com.example.project_shop.util.Constant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class ErrorResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorType ;
	private String message;
	private int status;


	public ErrorResponse(String errorType, String message, int status) {
		super();
		this.errorType = errorType;
		this.message = message;
		this.status = status;
	}

	public ErrorResponse(String errorType, String message) {
		super();
		this.errorType = errorType;
		this.message = message;
		this.status = Constant.Code.BAD_REQUEST;
	}

}
