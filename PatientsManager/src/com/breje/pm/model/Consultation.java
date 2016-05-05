package com.breje.pm.model;

import java.time.LocalDate;
import java.util.List;
import java.util.StringJoiner;

import com.breje.pm.util.AppHelper;

public class Consultation implements AppEntity {
	private String consID;
	private String patientSSN;
	private String diag;
	private List<String> meds;
	private LocalDate consultationDate;

	private static int CONS_NUMBER = 0;

	public Consultation(String PatientSSN, String diag, List<String> meds, LocalDate date) {
		this.consID = Integer.toString(CONS_NUMBER);
		this.patientSSN = PatientSSN;
		this.diag = diag;
		this.meds = meds;
		consultationDate = date;
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
		return consultationDate;
	}

	public void setConsultation_date(LocalDate date) {
		consultationDate = date;
	}

	@Override
	public String toString() {
		StringJoiner sj = new StringJoiner(", ");
		StringJoiner sjMeds = new StringJoiner(" \\+ ");
		for (String med : meds) {
			sjMeds.add(med);
		}
		sj.add(consID).add(patientSSN).add(diag).add(sjMeds.toString())
				.add(AppHelper.DATE_FORMAT.format(consultationDate));

		return sj.toString();
	}

}
