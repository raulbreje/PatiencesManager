package com.breje.pm.persistance.impl;

import java.util.ArrayList;
import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.Entity;
import com.breje.pm.model.Patient;
import com.breje.pm.persistance.Repository;
import com.breje.pm.util.AppHelper;

public class PatientRepository extends AbstractRepository implements Repository {

	private String patients = null;

	public PatientRepository(String fileName) {
		patients = fileName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(Entity elem) throws PatientsManagerException {
		Patient patient = (Patient) elem;
		List<Patient> listOfPersons = (List<Patient>) getEntities();
		listOfPersons.add(patient);
		List<String> listOfObjects = new ArrayList<>();
		listOfPersons.stream().map(String::valueOf).forEach(listOfObjects::add);
		try {
			super.save(listOfObjects, patients);
		} catch (Exception e) {
			throw (PatientsManagerException) e;
		}
	}

	@Override
	public List<?> getEntities() throws PatientsManagerException {
		List<Patient> listOfPatients = new ArrayList<>();
		List<String> tokens = null;
		try {
			tokens = super.load(patients);
		} catch (Exception e) {
			throw (PatientsManagerException) e;
		}
		for (String token : tokens) {
			String[] patientString = token.split(",");
			Patient patient = new Patient(patientString[AppHelper.PATIENT_NAME_INDEX].trim(),
					patientString[AppHelper.PATIENT_SSN_INDEX].trim(),
					patientString[AppHelper.PATIENT_ADDRESS_INDEX].trim());
			listOfPatients.add(patient);
		}
		return listOfPatients;
	}

	@Override
	public void cleanFile() throws PatientsManagerException {
		try {
			super.cleanFile(patients);
		} catch (Exception e) {
			throw (PatientsManagerException) e;
		}
	}

}
