package com.breje.pm.persistance.impl;

import java.io.IOException;
import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.AppEntity;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.ObjectTypes;
import com.breje.pm.model.Patient;
import com.breje.pm.persistance.Repository;
import com.breje.pm.util.AppHelper;

public class PatientRepository implements Repository {

	private String patients = null;
	
	@Override
	public void cleanFiles() throws PatientsManagerException {
		try {
			AppHelper.cleanFileContent(patients);
		} catch (IOException e) {
			throw new PatientsManagerException("IOException. Contact your administrator");
		}
	}

	@Override
	public List<Patient> getPatients() throws PatientsManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Consultation> getConsultations() throws PatientsManagerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ObjectTypes type, AppEntity elem) throws PatientsManagerException {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AppEntity> getEntities(ObjectTypes type) throws PatientsManagerException {
		// TODO Auto-generated method stub
		return null;
	}

}
