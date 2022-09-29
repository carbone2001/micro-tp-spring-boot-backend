package com.microtp.exceptions;

public class RequiredFieldException extends Exception {
	private static final long serialVersionUID = 1L;

	public RequiredFieldException(String fieldName) {
		super("Falta el campo requerido: "+fieldName);
	}
}
