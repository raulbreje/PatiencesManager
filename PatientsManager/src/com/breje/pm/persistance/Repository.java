package com.breje.pm.persistance;

import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.ObjectTypes;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.AppEntity;
import com.breje.pm.model.Patient;

/**
 * Created by Raul Breje on 03/23/2016.
 */
public interface Repository {

	List<AppEntity> getEntities(ObjectTypes type) throws PatientsManagerException;

	void save(ObjectTypes type, AppEntity elem) throws PatientsManagerException;

	@Deprecated
	List<Patient> getPatients() throws PatientsManagerException;

	@Deprecated
	List<Consultation> getConsultations() throws PatientsManagerException;

	@Deprecated
	void cleanFiles() throws PatientsManagerException;

}
