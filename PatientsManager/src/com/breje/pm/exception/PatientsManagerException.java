package com.breje.pm.exception;

/**
 * Created by Raul Breje on 03/22/2016.
 */
public class PatientsManagerException extends Exception {

	private static final long serialVersionUID = 4135526579901367290L;

	public PatientsManagerException() {
        super();
    }

    public PatientsManagerException(String message) {
        super(message);
    }

    public PatientsManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
