package com.breje.pm.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Consultation implements IAppElement {
	private String consID;
	private String patientSSN;
	private String diag;
	private List<String> meds;
	private LocalDate consultation_date;

	private static int CONS_NUMBER = 0;

	public Consultation() {
		consID = Integer.toString(CONS_NUMBER);
		patientSSN = "default_patientSSN";
		diag = "default_diag";
		meds = new ArrayList<>();
		consultation_date = LocalDate.now();
		CONS_NUMBER++;
	}

	public Consultation(String PatientSSN, String diag, List<String> meds, LocalDate date) {
		this.consID = Integer.toString(CONS_NUMBER);
		this.patientSSN = PatientSSN;
		this.diag = diag;
		this.meds = meds;
		consultation_date = date;
		CONS_NUMBER++;
	}

	public String getConsID() {
		return consID;
	}

	public void setConsID(String v_consID) {
		consID = v_consID;
	}

	public String getPatientSSN() {
		return patientSSN;
	}

	public void setPatientSSN(String patientSSN) {
		this.patientSSN = patientSSN;
	}

	public String getDiag() {
		return diag;
	}

	public void setDiag(String diag) {
		this.diag = diag;
	}

	public List<String> getMeds() {
		return meds;
	}

	public void setMeds(List<String> meds) {
		this.meds = meds;
	}

	public LocalDate getConsultation_date() {
		return consultation_date;
	}

	public void setConsultation_date(LocalDate date) {
		consultation_date = date;
	}

	public String toString() {
		StringJoiner sj = new StringJoiner(", ");
		StringBuilder sb = new StringBuilder();
		for (String med : meds) {
			sb.append(med).append(" \\+ ");
		}
		sj.add(consID).add(patientSSN).add(diag).add(sb.substring(0, sb.length() - 2).toString())
				.add(consultation_date.toString());
		return sj.toString();
	}

}
