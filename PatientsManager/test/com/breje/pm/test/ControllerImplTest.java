package com.breje.pm.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.breje.pm.controller.Controller;
import com.breje.pm.controller.impl.ControllerImpl;
import com.breje.pm.exception.PatientsManagerException;
import com.breje.pm.exception.ValidatorException;
import com.breje.pm.model.Patient;
import com.breje.pm.persistance.Repository;
import com.breje.pm.persistance.impl.RepositoryImpl;

public class ControllerImplTest {

	private static Repository repository = null;
	private static Controller controller = null;
	private Patient patient1 = null;
	private Patient patient2 = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Test cases for Controller workflow had been started.");
		repository = new RepositoryImpl("persistance/pat-tests.txt", "persistance/cons-tests.txt");
		System.out.println("Repository of application has been initialized.");
		controller = new ControllerImpl(repository);
		System.out.println("Controller of application has been initialized.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		controller = null;
		System.out.println("Controller of application has been disposed.");
		repository = null;
		System.out.println("Repository of application has been disposed.");
		System.out.println("Test cases for Controller workflow had been finished.");
	}

	@Before
	public void setUp() throws Exception {
		patient1 = new Patient("First User", "1990921314010", "acasa la el");
		patient2 = new Patient("Second User", "1890623336023", "acasa tot la el");
		System.out.println("Test case has been set up.");
	}

	@After
	public void tearDown() throws Exception {
		patient1 = null;
		patient2 = null;
		System.out.println("Test case has been finished.");
	}

	@Test
	public void testGetPatients() {
		System.out.println("Test case (testGetPatients) has been started.");
		assertTrue(true);
		System.out.println("Test case (testGetPatients) has been finished.");
	}

	@Test
	public void testAdd() {
		System.out.println("Test case (testAdd) has been started.");
		try {
			controller.add(patient1);
		} catch (PatientsManagerException | ValidatorException e) {
			e.printStackTrace();
		}
		Patient temporaryPatient = null;
		try {
			temporaryPatient = controller.getPatientBySSN(patient1.getSSN());
		} catch (PatientsManagerException e) {
			e.printStackTrace();
		}
		assertEquals(patient1, temporaryPatient);
		List<Patient> toRemovePatients = null;
		try {
			toRemovePatients = controller.getPatients();
		} catch (PatientsManagerException e) {
			e.printStackTrace();
		}
		toRemovePatients.remove(patient1);
		
		try {
			controller.add(patient2);
		} catch (PatientsManagerException | ValidatorException e) {
			e.printStackTrace();
		}
		List<Patient> currentPatients = null;
		try {
			currentPatients = controller.getPatients();
		} catch (PatientsManagerException e) {
			e.printStackTrace();
		}
		for (Patient p : currentPatients) {
			if (p.equals(patient2)) {
				assertEquals(patient1, p);
				break;
			}
		}
		System.out.println("Test case (testAdd) has been finished.");
	}

}