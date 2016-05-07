package com.breje.pm.exception;

public class AbstractRepositoryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5525293106326046126L;

	public AbstractRepositoryException() {
		super();
	}

	public AbstractRepositoryException(String message) {
		super(message);
	}

	public AbstractRepositoryException(String message, Throwable cause) {
		super(message, cause);
	}

}
