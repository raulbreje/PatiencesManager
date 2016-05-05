package com.breje.pm.controller.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.breje.pm.controller.Controller;
import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.exception.ValidatorException;
import com.breje.pm.model.ObjectTypes;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.AppEntity;
import com.breje.pm.model.Patient;
import com.breje.pm.model.Validator;
import com.breje.pm.persistance.Repository;
import com.breje.pm.util.AppHelper;

public class ControllerImpl implements Controller {

	private List<Patient> patients;
	private List<Consultation> consultations;
	private Repository repository;

	public ControllerImpl(Repository repository) {
		this.repository = repository;
	}

	public List<Patient> getPatients() throws PatientsManagerException {
		if (patients == null) {
			patients = repository.getPatients();
		}
		return patients;
	}

	public List<Consultation> getConsultations() throws PatientsManagerException {
		if (consultations == null) {
			consultations = repository.getConsultations();
		}
		return consultations;
	}

	public Patient getPatientBySSN(String SSN) throws PatientsManagerException {
		if (patients == null) {
			patients = repository.getPatients();
		}
		for (Patient p : getPatients()) {
			if (SSN.equals(p.getSSN())) {
				return p;
			}
		}
		return null;
	}

	public Consultation getConsultationByID(String ID) throws PatientsManagerException {
		if (consultations == null) {
			consultations = repository.getConsultations();
		}
		for (Consultation c : consultations) {
			if (ID.equals(c.getConsID())) {
				return c;
			}
		}
		return null;
	}

	public void add(AppEntity elem) throws PatientsManagerException, ValidatorException {
		if (elem instanceof Patient) {
			addPatient((Patient) elem);
		} else if (elem instanceof Consultation) {
			addConsultation((Consultation) elem);
		} else {
			throw new PatientsManagerException("Whatever. Contact your administrator.");
		}
	}

	private void addPatient(Patient patient) throws ValidatorException, PatientsManagerException {
		Validator.validatePerson(patient);
		if (getPatientBySSN(patient.getSSN()) == null) {
			patients.add(patient);
			repository.save(ObjectTypes.PATIENT, patient);
		} else {
			throw new PatientsManagerException("Patient already exists. Contact your administrator.");
		}
	}

	private void addConsultation(Consultation consultation) throws PatientsManagerException, ValidatorException {
		Validator.validateConsultation(consultation);
		if (getPatientBySSN(consultation.getPatientSSN()) != null
				&& getConsultationByID(consultation.getConsID()) == null) {
			consultations.add(consultation);
			repository.save(ObjectTypes.CONSULTATION, consultation);
			Patient p = getPatientBySSN(consultation.getPatientSSN());
			p.setConsNum(p.getConsNum() + 1);
		} else {
			throw new PatientsManagerException("Consultation ID already exists. Contact your administrator.");
		}
	}

	public List<Patient> getPatientsWithDisease(String disease) throws PatientsManagerException {
		List<Consultation> cs = getConsultations();
		List<Patient> pat = new ArrayList<>();
		List<Patient> sortedPat = new ArrayList<>();
		if (!AppHelper.isNull(disease)) {
			cs.stream().filter(c -> c.getDiag().toLowerCase().contains(disease.toLowerCase())).forEach(c -> {
				Patient p = null;
				try {
					p = getPatientBySSN(c.getPatientSSN());
				} catch (PatientsManagerException e) {
					e.printStackTrace();
				}
				if (!pat.contains(p)) {
					pat.add(p);
				}
			});
		} else {
			throw new PatientsManagerException("Disease null. Contact your administrator.");
		}
		Comparator<Patient> byConsNumber = (p1, p2) -> Integer.compare(p1.getConsNum(), p2.getConsNum());
		pat.stream().sorted(byConsNumber).forEach(sp -> sortedPat.add(sp));
		Collections.reverse(sortedPat);
		return sortedPat;
	}

}
