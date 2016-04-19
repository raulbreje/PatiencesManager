package com.breje.pm.model;

import com.breje.pm.exception.ValidatorException;
import com.breje.pm.util.AppHelper;

/**
 * Created by Raul Breje on 03/23/2016.
 */
public class Validator {

	public static final String PATTERN_SSN = "[1|2]+[0-9]+[0-9]+[0[1-9]+|1[012]+]+[[012]+[0-9]+|3[01]+]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+";

	public static final String PATTERN_NAME = "^[a-zA-Z\\s]+";

	public static void validatePerson(Patient patient) throws ValidatorException {
		if (AppHelper.isNull(patient.getName()) || !patient.getName().matches(PATTERN_NAME)) {
			throw new ValidatorException("Invalid Name.");
		}
		if (!patient.getSSN().matches(PATTERN_SSN)) {
			throw new ValidatorException("Invalid SSN.");
		}
		if (AppHelper.isNull(patient.getAddress())) {
			throw new ValidatorException("Invalid address.");
		}
	}

	public static void validateConsultation(Consultation consultation) throws ValidatorException {
		if (consultation.getConsID() == null) {
			throw new ValidatorException("Invalid Cons ID.");
		}
		if (consultation.getPatientSSN() == null) {
			throw new ValidatorException("Invalid Patient SSN.");
		}
		if (consultation.getDiag() == null) {
			throw new ValidatorException("Invalid Diag.");
		}
		if (consultation.getMeds().isEmpty()) {
			throw new ValidatorException("Empty meds.");
		}
	}

}
