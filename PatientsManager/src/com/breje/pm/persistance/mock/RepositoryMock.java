package com.breje.pm.persistance.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.ObjectTypes;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.AppEntity;
import com.breje.pm.model.Patient;
import com.breje.pm.persistance.Repository;
import com.breje.pm.util.AppHelper;

public class RepositoryMock implements Repository {

	private List<Patient> patients = null;
	private List<Consultation> consultations = null;

	public RepositoryMock() {
		patients = new ArrayList<>();
		consultations = new ArrayList<>();
		initMock();
	}

	private void initMock() {
		Patient p1 = new Patient("First User", "1900909314010", "First Hometown");
		Patient p2 = new Patient("Second User", "1911010354010", "Second Hometown");
		Patient p3 = new Patient("Third User", "1921112344010", "Third Hometown");
		Patient p4 = new Patient("Fourth User", "1931213334010", "Fourth Hometown");
		Patient p5 = new Patient("Fifth User", "1940114324010", "Fifth Hometown");
		Consultation c1 = new Consultation("1900909314010", "nothing1", Arrays.asList("med1"),
				LocalDate.parse("Apr 16 2016", AppHelper.DATE_FORMAT));
		Consultation c2 = new Consultation("1911010354010", "nothing2", Arrays.asList("med2+med3"),
				LocalDate.parse("Apr 17 2016", AppHelper.DATE_FORMAT));
		Consultation c3 = new Consultation("1900909314010", "nothing3", Arrays.asList("med4+med5+med6"),
				LocalDate.parse("Apr 18 2016", AppHelper.DATE_FORMAT));
		patients.add(p1);
		patients.add(p2);
		patients.add(p3);
		patients.add(p4);
		patients.add(p5);
		consultations.add(c1);
		consultations.add(c2);
		consultations.add(c3);
	}

	@Override
	public void cleanFiles() throws PatientsManagerException {
		System.out.println("Mock cleanFiles()");
	}

	@Override
	public List<Patient> getPatients() throws PatientsManagerException {
		return patients;
	}

	@Override
	public List<Consultation> getConsultations() throws PatientsManagerException {
		return consultations;
	}

	@Override
	public void save(ObjectTypes type, AppEntity elem) throws PatientsManagerException {
		switch (type) {
		case PATIENT:
			patients.add((Patient) elem);
			break;
		case CONSULTATION:
			consultations.add((Consultation) elem);
			break;
		default:
			break;
		}

	}

	@Override
	public List<AppEntity> getEntities(ObjectTypes type) throws PatientsManagerException {
		// TODO Auto-generated method stub
		return null;
	}

}
