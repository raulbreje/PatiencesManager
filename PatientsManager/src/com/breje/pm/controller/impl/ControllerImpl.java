package com.breje.pm.controller.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.breje.pm.controller.Controller;
import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.exception.ValidatorException;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.Entity;
import com.breje.pm.model.Patient;
import com.breje.pm.model.Validator;
import com.breje.pm.persistance.Repository;
import com.breje.pm.util.AppHelper;

public class ControllerImpl implements Controller {

	private List<Patient> patients;
	private List<Consultation> consultations;
	@Deprecated
	private Repository repository;

	private Repository patientsRepository;
	private Repository consultationsRepository;

	public ControllerImpl(Repository patientRepository, Repository consultationRepository) {
		this.patientsRepository = patientRepository;
		this.consultationsRepository = consultationRepository;
	}

	@Deprecated
	public ControllerImpl(Repository repository) {
		this.repository = repository;
	}

	@SuppressWarnings("unchecked")
	public List<Patient> getPatients() throws PatientsManagerException {
		if (patients == null) {
			patients = (List<Patient>) patientsRepository.getEntities();
		}
		return patients;
	}

	@SuppressWarnings("unchecked")
	public List<Consultation> getConsultations() throws PatientsManagerException {
		if (consultations == null) {
			consultations = (List<Consultation>) consultationsRepository.getEntities();
		}
		return consultations;
	}

	@SuppressWarnings("unchecked")
	public Patient getPatientBySSN(String SSN) throws PatientsManagerException {
		if (patients == null) {
			patients = (List<Patient>) patientsRepository.getEntities();
		}
		for (Patient p : getPatients()) {
			if (SSN.equals(p.getSSN())) {
				return p;
			}
		}
		throw new PatientsManagerException(
				"The patient with " + SSN + " SSN doesn't exists in repository. Contact your administrator.");
	}

	@SuppressWarnings("unchecked")
	public Consultation getConsultationByID(String ID) throws PatientsManagerException {
		if (consultations == null) {
			consultations = (List<Consultation>) consultationsRepository.getEntities();
		}
		for (Consultation c : consultations) {
			if (ID.equals(c.getConsID())) {
				return c;
			}
		}
		throw new PatientsManagerException(
				"The consultation with " + ID + " ID doesn't exists in repository. Contact your administrator.");
	}

	public void add(Entity elem) throws PatientsManagerException, ValidatorException {
		if (elem instanceof Patient) {
			addPatient((Patient) elem);
		} else if (elem instanceof Consultation) {
			addConsultation((Consultation) elem);
		}
	}

	private void addPatient(Patient patient) throws ValidatorException, PatientsManagerException {
		Validator.validatePerson(patient);
		try {
			getPatientBySSN(patient.getSSN());
		} catch (Exception e) {
			throw e;
		}
		// if (patients == null) {
		// patients = (List<Patient>) patientsRepository.getEntities();
		// }
		patients.add(patient);
		patientsRepository.save(patient);
	}

	@SuppressWarnings("unchecked")
	private void addConsultation(Consultation consultation) throws PatientsManagerException, ValidatorException {
		Validator.validateConsultation(consultation);
		if (consultations == null) {
			consultations = (List<Consultation>) consultationsRepository.getEntities();
		}
		consultations.add(consultation);
		consultationsRepository.save(consultation);
		Patient p = getPatientBySSN(consultation.getPatientSSN());
		p.setConsNum(p.getConsNum() + 1);
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
