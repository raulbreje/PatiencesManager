package model;

import exception.ValidatorException;

/**
 * Created by Raul Breje on 03/23/2016.
 */
public class Validator {

    private static final String PATTERN_SSN = "[1|2]+[0-9]+[0-9]+[0[1-9]+|1[012]+]+[[012]+[0-9]+|3[01]+]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+[0-9]+";

    public static void validatePerson(Patient patient) throws ValidatorException {
        // TODO refactor
        if (patient.getName() == null || !patient.getName().matches("^[A-Za-z ']*$")) {
            throw new ValidatorException("Invalid Name.");
        }
        // TODO refactor
        if (patient.getSSN().matches(PATTERN_SSN)) {
            throw new ValidatorException("Invalid SSN.");
        }
        if (patient.getAddress() == null) {
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
