package com.microtp.dtos.api;

import lombok.Data;

@Data
public class ApiError {
	private String message;
	private String errorCode;

	public ApiError(String message, String errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}
}
