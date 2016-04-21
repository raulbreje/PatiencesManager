package com.breje.pm.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.breje.pm.exception.ValidatorException;
import com.breje.pm.model.Patient;
import com.breje.pm.model.Validator;
import com.breje.pm.persistance.Repository;

public class ValidatorTest {

	private Patient patient1 = null;
	private Patient patient2 = null;
	private Patient patient3 = null;
	private Patient patient4 = null;
	private Patient patient5 = null;
	private Patient patient6 = null;
	private Patient patient7 = null;
	private Patient patient8 = null;
	private Patient patient9 = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Test cases for Validator workflow had been started.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Test cases for Validator workflow had been finished.");
	}

	@Before
	public void setUp() throws Exception {
		patient1 = new Patient("First User", "1990921314010", "acasssfaa2");
		patient2 = new Patient("Second User", "1890623023", "acasa2");
		patient3 = new Patient(null, "1990921314010", "acasa3");
		patient4 = new Patient("Fourth User", "1880623336023", null);
		patient5 = new Patient("First User", "1990921314010", "ac");
		patient6 = new Patient("Second User", "1990921314010", "acas");
		patient7 = new Patient("dd", "1990921314010", "acasa3");
		patient8 = new Patient("aaaaaaaaaaaaaaaaaaaaaaaa", "1990921314010", "acasa3");
		patient9 = new Patient("/.,s", "1990921314010", "acasa3");
	}

	@After
	public void tearDown() throws Exception {
		patient1 = null;
		patient2 = null;
		patient3 = null;
		patient4 = null;
		patient5 = null;
		patient6 = null;
		patient7 = null;
		patient8 = null;
		patient9 = null;
	}
	
	@Test
	public void testValidatePerson1() {
		boolean isValid = true;
		try {
			Validator.validatePerson(patient1);
		} catch (ValidatorException e) {
			System.err.println(e);
			isValid = false;
		}
		assertTrue(isValid);
	}
	
	@Test
	public void testValidatePerson2() {
		boolean isInvalid = false;
		try {
			Validator.validatePerson(patient2);
		} catch (ValidatorException e) {
			isInvalid = true;
		}
		assertTrue(isInvalid);
	}
	
	@Test
	public void testValidatePerson3() {
		boolean isInvalid = false;
		try {
			Validator.validatePerson(patient3);
		} catch (ValidatorException e) {
			isInvalid = true;
		}
		assertTrue(isInvalid);
	}
	
	@Test
	public void testValidatePerson4() {
		boolean isInvalid = false;
		try {
			Validator.validatePerson(patient4);
		} catch (ValidatorException e) {
			isInvalid = true;
		}
		assertTrue(isInvalid);
	}
	
	@Test
	public void testValidatePerson5() {
		boolean isInvalid = false;
		try {
			Validator.validatePerson(patient5);
		} catch (ValidatorException e) {
			isInvalid = true;
		}
		assertTrue(isInvalid);
	}
	
	@Test
	public void testValidatePerson6() {
		boolean isInvalid = false;
		try {
			Validator.validatePerson(patient6);
		} catch (ValidatorException e) {
			isInvalid = true;
		}
		assertTrue(isInvalid);
	}
	
	@Test
	public void testValidatePerson7() {
		boolean isInvalid = false;
		try {
			Validator.validatePerson(patient7);
		} catch (ValidatorException e) {
			isInvalid = true;
		}
		assertTrue(isInvalid);
	}
	
	@Test
	public void testValidatePerson8() {
		boolean isInvalid = false;
		try {
			Validator.validatePerson(patient8);
		} catch (ValidatorException e) {
			isInvalid = true;
		}
		assertTrue(isInvalid);
	}
	
	@Test
	public void testValidatePerson9() {
		boolean isInvalid = false;
		try {
			Validator.validatePerson(patient9);
		} catch (ValidatorException e) {
			isInvalid = true;
		}
		assertTrue(isInvalid);
	}
}
