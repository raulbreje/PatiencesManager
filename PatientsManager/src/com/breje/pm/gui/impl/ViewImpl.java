package com.breje.pm.gui.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import com.breje.pm.controller.Controller;
import com.breje.pm.gui.View;
import com.breje.pm.model.Consultation;
import com.breje.pm.model.Patient;
import com.breje.pm.util.AppHelper;

/**
 * Created by Raul Breje on 03/17/2016.
 */
public class ViewImpl implements View {

	private Controller controller = null;
	private Scanner keyboard = null;

	public ViewImpl(Controller controller) {
		this.controller = controller;
		keyboard = new Scanner(System.in);
	}

	private String menu() {
		StringJoiner sj = new StringJoiner("\n");
		sj.add("Menu:");
		sj.add("1 - Add Patient");
		sj.add("2 - Add Consultation");
		sj.add("3 - Get patiences");
		sj.add("0 - Exit");
		sj.add("Give you command: ");
		return sj.toString();
	}

	public void run() {
		while (true) {
			System.out.println(menu());
			int cmd = keyboard.nextInt();
			keyboard.nextLine();
			switch (cmd) {
			case 0:
				System.exit(0);
				break;
			case 1:
				System.out.println("Give name: ");
				String name = keyboard.nextLine();
				System.out.println("Give CNP: ");
				String CNP = keyboard.nextLine();
				System.out.println("Give address: ");
				String address = keyboard.nextLine();
				Patient patient = new Patient(name.trim(), CNP.trim(), address.trim());
				try {
					controller.add(patient);
				} catch (Exception e) {
					System.err.println(e);
				}
				break;
			case 2:
				System.out.println("Give patient SSN: ");
				String patientSSN = keyboard.nextLine();
				System.out.println("Give the diagnostic: ");
				String diagnostic = keyboard.nextLine();
				System.out.println("Give the meds: ");
				String meds = keyboard.nextLine();
				System.out.println("Give date (MMM dd, yyyy): ");
				String stringDate = keyboard.nextLine();
				try {
					LocalDate date = LocalDate.parse(stringDate.trim(), AppHelper.DATE_FORMAT);
					List<String> medsList = new ArrayList<>(Arrays.asList(meds.trim().split(",")));
					Consultation consultation = new Consultation(patientSSN.trim(), diagnostic.trim(), medsList, date);
					controller.add(consultation);
				} catch (Exception e) {
					System.err.println(e);
				}
				break;
			case 3:
				System.out.println("Give the disease: ");
				String filterDiagnostic = keyboard.nextLine();
				try {
					System.out.println(controller.getPatientsWithDisease(filterDiagnostic.trim()));
				} catch (Exception e) {
					System.err.println(e);
				}
				break;
			default:
				System.out.println("Wrong command. Please retry: ");
				break;
			}
		}
	}

}
