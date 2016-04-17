package com.breje.pm.exception;

/**
 * Created by Raul Breje on 03/23/2016.
 */
public class ValidatorException extends Exception {

	private static final long serialVersionUID = -7589061724821407238L;

	public ValidatorException() {
		super();
	}

	public ValidatorException(String message) {
		super(message);
	}

	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}
}
