package com.breje.pm.controller;

import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.exception.ValidatorException;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.IAppElement;
import com.breje.pm.model.Patient;

/**
 * Created by Raul Breje on 03/23/2016.
 */
public interface Controller {

	List<Patient> getPatients() throws PatientsManagerException;

	List<Consultation> getConsultations() throws PatientsManagerException;

	Patient getPatientBySSN(String SSN) throws PatientsManagerException;

	Consultation getConsultationByID(String ID);

	void add(IAppElement elem) throws PatientsManagerException, ValidatorException;

	List<Patient> getPatientsWithDisease(String disease) throws PatientsManagerException;

}
