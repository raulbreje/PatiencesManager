package com.breje.pm.persistance.mock;

import java.util.ArrayList;
import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.Entity;
import com.breje.pm.model.Patient;
import com.breje.pm.persistance.Repository;
import com.breje.pm.persistance.impl.AbstractRepository;

public class PatientRepositoryMock extends AbstractRepository implements Repository {

	private List<Patient> patients = null;

	public PatientRepositoryMock() {
		patients = new ArrayList<>();
		initMock();
	}

	private void initMock() {
		Patient p1 = new Patient("First User", "1900909314010", "First Hometown");
		Patient p2 = new Patient("Second User", "1911010354010", "Second Hometown");
		Patient p3 = new Patient("Third User", "1921112344010", "Third Hometown");
		Patient p4 = new Patient("Fourth User", "1931213334010", "Fourth Hometown");
		Patient p5 = new Patient("Fifth User", "1940114324010", "Fifth Hometown");
		patients.add(p1);
		patients.add(p2);
		patients.add(p3);
		patients.add(p4);
		patients.add(p5);
	}

	@Override
	public void save(Entity elem) throws PatientsManagerException {
		patients.add((Patient) elem);
	}

	@Override
	public List<?> getEntities() throws PatientsManagerException {
		return patients;
	}

	@Override
	public void cleanFile() throws PatientsManagerException {

	}

}
