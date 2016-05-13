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
import com.breje.pm.persistance.mock.ConsultationRepositoryMock;
import com.breje.pm.persistance.mock.PatientRepositoryMock;

public class ControllerImplTest {

	private static Repository patientRepository = null;
	private static Repository consultationRepository = null;
	private static Controller controller = null;
	private Patient patient1 = null;
	private Patient patient2 = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Test cases for Controller workflow had been started.");
		consultationRepository = new ConsultationRepositoryMock();
		patientRepository = new PatientRepositoryMock();
		System.out.println("Repository of application has been initialized.");
		controller = new ControllerImpl(patientRepository, consultationRepository);
		System.out.println("Controller of application has been initialized.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		controller = null;
		System.out.println("Controller of application has been disposed.");
		patientRepository = null;
		consultationRepository = null;
		System.out.println("Repository of application has been disposed.");
		System.out.println("Test cases for Controller workflow had been finished.");
	}

	@Before
	public void setUp() throws Exception {
		patient1 = new Patient("First User", "1590921314010", "acasa la el3");
		patient2 = new Patient("Second User", "1690623336023", "acasa tot la el3");
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
	public void testAdd1() {
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
		System.out.println("Test case (testAdd) has been finished.");
	}

	@Test
	public void testAdd2() {
		System.out.println("Test case (testAdd) has been started.");
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
				assertEquals(patient2, p);
				break;
			}
		}
		System.out.println("Test case (testAdd) has been finished.");
	}

	@Test
	public void testAdd3() {
		System.out.println("Test case (testAdd) has been started.");
		boolean alreadyExists = false;
		try {
			controller.add(patient1);
		} catch (PatientsManagerException | ValidatorException e) {
			alreadyExists = true;
		}
		assertTrue(alreadyExists);
		System.out.println("Test case (testAdd) has been finished.");
	}

}
