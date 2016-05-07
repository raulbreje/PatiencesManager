package com.breje.pm.persistance;

import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.Entity;

/**
 * Created by Raul Breje on 03/23/2016.
 * 
 */
public interface Repository {

	void save(Entity elem) throws PatientsManagerException;

	List<?> getEntities() throws PatientsManagerException;

	void cleanFile() throws PatientsManagerException;

}
