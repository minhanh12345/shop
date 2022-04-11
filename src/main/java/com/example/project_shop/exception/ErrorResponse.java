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
	private String messageEN;
	private String messageVN;
	private int errorCode;
	private int status;

	public ErrorResponse(int errorCode,String errorType, String messageEN, String messageVN) {
		this.errorType = errorType;
		this.messageEN = messageEN;
		this.messageVN = messageVN;
		this.errorCode = errorCode;
	}
	public ErrorResponse(String errorType, String message) {
		super();
		this.errorType = errorType;
		this.messageEN = messageEN;
		this.messageVN = messageVN;
		this.status = Constant.Code.BAD_REQUEST;
	}

}
