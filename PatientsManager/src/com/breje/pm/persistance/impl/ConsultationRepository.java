package com.breje.pm.persistance.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.Entity;
import com.breje.pm.persistance.Repository;
import com.breje.pm.util.AppHelper;

public class ConsultationRepository extends AbstractRepository implements Repository {

	private String consultations = null;

	public ConsultationRepository(String fileName) {
		consultations = fileName;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void save(Entity elem) throws PatientsManagerException {
		Consultation consultation = (Consultation) elem;
		List<Consultation> listOfConsultation = (List<Consultation>) getEntities();
		listOfConsultation.add(consultation);
		List<String> listOfObjects = new ArrayList<>();
		listOfConsultation.stream().map(String::valueOf).forEach(listOfObjects::add);
		try {
			super.save(listOfObjects, consultations);
		} catch (Exception e) {
			throw (PatientsManagerException) e;
		}
	}

	@Override
	public List<?> getEntities() throws PatientsManagerException {
		List<Consultation> listOfConsultations = new ArrayList<>();
		List<String> tokens = null;
		try {
			tokens = super.load(consultations);
		} catch (Exception e) {
			throw (PatientsManagerException) e;
		}
		for (String token : tokens) {
			List<String> medString = new ArrayList<>();
			String[] parts = token.split(",");
			String[] meds = parts[3].split("\\+");
			for (String med : meds) {
				medString.add(med.trim());
			}
			LocalDate date = LocalDate.parse(parts[4].trim(), AppHelper.DATE_FORMAT);
			Consultation consultation = new Consultation(parts[1].trim(), parts[2].trim(), medString, date);
			listOfConsultations.add(consultation);
		}
		return listOfConsultations;
	}

	@Override
	public void cleanFile() throws PatientsManagerException {
		try {
			super.cleanFile(consultations);
		} catch (Exception e) {
			throw (PatientsManagerException) e;
		}
	}

}
