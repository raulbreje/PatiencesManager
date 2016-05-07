package com.breje.pm.persistance.mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.Entity;
import com.breje.pm.persistance.Repository;
import com.breje.pm.persistance.impl.AbstractRepository;
import com.breje.pm.util.AppHelper;

public class ConsultationRepositoryMock extends AbstractRepository implements Repository {

	private List<Consultation> consultations = null;

	public ConsultationRepositoryMock() {
		consultations = new ArrayList<>();
		initMock();
	}

	private void initMock() {
		Consultation c1 = new Consultation("1900909314010", "nothing1", Arrays.asList("med1"),
				LocalDate.parse("Apr 16 2016", AppHelper.DATE_FORMAT));
		Consultation c2 = new Consultation("1911010354010", "nothing2", Arrays.asList("med2+med3"),
				LocalDate.parse("Apr 17 2016", AppHelper.DATE_FORMAT));
		Consultation c3 = new Consultation("1900909314010", "nothing3", Arrays.asList("med4+med5+med6"),
				LocalDate.parse("Apr 18 2016", AppHelper.DATE_FORMAT));
		consultations.add(c1);
		consultations.add(c2);
		consultations.add(c3);
	}

	@Override
	public void save(Entity elem) throws PatientsManagerException {
		consultations.add((Consultation) elem);
	}

	@Override
	public List<?> getEntities() throws PatientsManagerException {
		return consultations;
	}

	@Override
	public void cleanFile() throws PatientsManagerException {

	}

}
