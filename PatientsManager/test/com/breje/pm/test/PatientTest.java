package com.breje.pm.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.breje.pm.model.Patient;

public class PatientTest {

	private Patient testPatient1 = null;

	private Patient testPatient2 = null;

	@Before
	public void setUp() throws Exception {
		testPatient1 = new Patient("Test User", "1940923314010", "Marasti Cluj");
		testPatient2 = new Patient("Non Test User", "1940923314011", "Manastur Cluj");
	}

	@Test
	public void testGetPatientID() {
		String expected1 = "1940923314010";
		String expected2 = "1940923314011";
		String actual1 = testPatient1.getPatientID();
		String actual2 = testPatient2.getPatientID();
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void testGetName() {
		String expected1 = "Test User";
		String expected2 = "Non Test User";
		String actual1 = testPatient1.getName();
		String actual2 = testPatient2.getName();
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void testSetName() {
		String expected1 = "Test User New";
		testPatient1.setName(expected1);
		String actual1 = testPatient1.getName();
		assertEquals(expected1, actual1);

		String expected2 = "Non Test User New";
		testPatient2.setName(expected2);
		String actual2 = testPatient2.getName();
		assertEquals(expected2, actual2);
	}

	@Test
	public void testGetSSN() {
		String expected1 = "1940923314010";
		String expected2 = "1940923314011";
		String actual1 = testPatient1.getSSN();
		String actual2 = testPatient2.getSSN();
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void testSetSSN() {
		String expected1 = "1940923383510";
		testPatient1.setSSN(expected1);
		String actual1 = testPatient1.getSSN();
		assertEquals(expected1, actual1);
		actual1 = testPatient1.getPatientID();
		assertEquals(expected1, actual1);

		String expected2 = "1958472637485";
		testPatient2.setSSN(expected2);
		String actual2 = testPatient2.getSSN();
		assertEquals(expected2, actual2);
		actual2 = testPatient2.getPatientID();
		assertEquals(expected2, actual2);
	}

	@Test
	public void testGetAddress() {
		String expected1 = "Marasti Cluj";
		String expected2 = "Manastur Cluj";
		String actual1 = testPatient1.getAddress();
		String actual2 = testPatient2.getAddress();
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void testSetAddress() {
		String expected1 = "Marasti New";
		testPatient1.setAddress(expected1);
		String actual1 = testPatient1.getAddress();
		assertEquals(expected1, actual1);

		String expected2 = "Grigorescu New";
		testPatient2.setAddress(expected2);
		String actual2 = testPatient2.getAddress();
		assertEquals(expected2, actual2);
	}

	@Test
	public void testGetConsNum() {
		int expected1 = 0;
		int expected2 = 0;
		int actual1 = testPatient1.getConsNum();
		int actual2 = testPatient2.getConsNum();
		assertEquals(expected1, actual1);
		assertEquals(expected2, actual2);
	}

	@Test
	public void testSetConsNum() {
		int expected1 = 3;
		testPatient1.setConsNum(expected1);
		int actual1 = testPatient1.getConsNum();
		assertEquals(expected1, actual1);

		int expected2 = 4;
		testPatient2.setConsNum(expected2);
		int actual2 = testPatient2.getConsNum();
		assertEquals(expected2, actual2);
	}

}
