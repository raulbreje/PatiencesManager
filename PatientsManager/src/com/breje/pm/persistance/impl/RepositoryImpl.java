package com.breje.pm.persistance.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.AppObjectTypes;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.IAppElement;
import com.breje.pm.model.Patient;
import com.breje.pm.persistance.Repository;
import com.breje.pm.util.AppHelper;

public class RepositoryImpl implements Repository {

	private String patients;
	private String consultations;

	public RepositoryImpl(String patients, String consultations) {
		this.patients = patients;
		this.consultations = consultations;
	}

	@Override
	public void cleanFiles() throws PatientsManagerException {
		try {
			Files.deleteIfExists(Paths.get(patients));
			Files.deleteIfExists(Paths.get(consultations));
			File filePat = new File(patients);
			filePat.createNewFile();
			File fileCons = new File(consultations);
			fileCons.createNewFile();
		} catch (IOException e) {
			throw new PatientsManagerException("IOException. Contact your administrator");
		}
	}

	@Override
	public List<Patient> getPatients() throws PatientsManagerException {
		List<Patient> lp = new ArrayList<>();
		List<String> tokens = load(AppObjectTypes.PATIENT);
		for (String elem : tokens) {
			String[] pat = elem.split(",");
			lp.add(new Patient(pat[0].trim(), pat[1].trim(), pat[2].trim()));
		}
		return lp;
	}

	private List<String> load(AppObjectTypes type) throws PatientsManagerException {
		switch (type) {
		case PATIENT:
			return getObjectFromFile(patients);
		case CONSULTATION:
			return getObjectFromFile(consultations);
		default:
			throw new PatientsManagerException("Type of object error. Contact your administrator");
		}
	}

	private List<String> getObjectFromFile(String fileName) throws PatientsManagerException {
		List<String> listOfPatients = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.forEach(listOfPatients::add);
		} catch (IOException e) {
			throw new PatientsManagerException("Wrong reading object. Contact your administrator");
		}
		return listOfPatients;
	}

	@Override
	public List<Consultation> getConsultations() throws PatientsManagerException {
		List<Consultation> lp = new ArrayList<>();
		List<String> tokens = load(AppObjectTypes.CONSULTATION);
		for (String elem : tokens) {
			List<String> med = new ArrayList<>();
			String[] pat = elem.split(",");
			String[] meds = pat[3].split("\\+");
			for (String m : meds) {
				med.add(m.trim());
			}
			LocalDate date = LocalDate.parse(pat[4].trim(), AppHelper.DATE_FORMAT);
			Consultation c = new Consultation(pat[1].trim(), pat[2].trim(), med, date);
			lp.add(c);
		}
		return lp;
	}

	@Override
	public void save(AppObjectTypes type, IAppElement elem) throws PatientsManagerException {
		switch (type) {
		case PATIENT:
			saveObjectToFile(patients, elem);
			break;
		case CONSULTATION:
			saveObjectToFile(consultations, elem);
			break;
		default:
			throw new PatientsManagerException("Type of object error. Contact your administrator");
		}
	}

	private void saveObjectToFile(String fileName, IAppElement elem) throws PatientsManagerException {
		if (elem instanceof Patient) {
			Patient p = (Patient) elem;
			List<Patient> lp = getPatients();
			lp.add(p);
			List<String> lps = new ArrayList<>();
			lp.stream().map(String::valueOf).forEach(lps::add);
			try {
				Files.write(Paths.get(fileName), lps);
			} catch (IOException e) {
				throw new PatientsManagerException("Whatever. Contact your administrator.");
			}
		} else if (elem instanceof Consultation) {
			Consultation c = (Consultation) elem;
			List<Consultation> lc = getConsultations();
			lc.add(c);
			List<String> lcs = new ArrayList<>();
			lc.stream().map(String::valueOf).forEach(lcs::add);
			try {
				Files.write(Paths.get(fileName), lcs);
			} catch (IOException e) {
				throw new PatientsManagerException("Whatever. Contact your administrator.");
			}
		} else {
			throw new PatientsManagerException("Whatever. Contact your administrator.");
		}

	}

}
