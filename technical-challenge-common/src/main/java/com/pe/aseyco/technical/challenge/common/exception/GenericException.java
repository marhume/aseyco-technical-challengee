package com.pe.aseyco.technical.challenge.common.exception;

public class GenericException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;

	private String errorCode;

	public GenericException() {
		super();
	}

	public GenericException(Throwable cause) {
		super(cause);
	}

	public GenericException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
	}
	
	public GenericException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
