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

	void cleanFiles() throws PatientsManagerException;

	List<Patient> getPatients() throws PatientsManagerException;

	List<Consultation> getConsultations() throws PatientsManagerException;

	void save(ObjectTypes type, AppEntity elem) throws PatientsManagerException;

}
