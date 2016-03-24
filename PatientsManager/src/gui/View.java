package gui;

import controller.IController;
import controller.doctorController;
import exception.PatientsManagerException;
import exception.ValidatorException;
import model.Consultation;
import model.Patient;
import util.AppUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Raul Breje on 03/17/2016.
 */
public class View implements IView {

    @Deprecated
    private doctorController controller;

    private IController controller2;
    private Scanner keyboard = new Scanner(System.in);

    @Deprecated
    public View(doctorController controller) {
        this.controller = controller;
    }

    public View(IController controller) {
        controller2 = controller;
    }

    private String menu() {
        String menu = "Menu:\n";
        menu += "1 - Add Patient\n";
        menu += "2 - Add Consultation\n";
        menu += "3 - Get patiences\n";
        menu += "0 - Exit\n";
        menu += "Give you command: ";
        return menu;
    }

    public void run2() {
        System.out.println(menu());
        while (true) {
            int cmd = keyboard.nextInt();
            switch (cmd) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Give name: ");
                    String name = keyboard.next();
                    System.out.println("Give CNP: ");
                    String CNP = keyboard.next();
                    System.out.println("Give address: ");
                    String address = keyboard.nextLine();
                    Patient p = new Patient(name.trim(), CNP.trim(), address.trim());
                    try {
                        controller2.add(p);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 2:

                    System.out.println("Give consID: ");
                    String consID = keyboard.nextLine();
                    System.out.println("Give SSN: ");
                    String patSSN = keyboard.nextLine();
                    System.out.println("Give diagnostic: ");
                    String diag = keyboard.nextLine();
                    System.out.println("Give meds: ");
                    String meds = keyboard.nextLine();
                    System.out.println("Give date (dd-MMM-yyyy): ");
                    String dateS = keyboard.nextLine();
                    try {
                        LocalDate date = LocalDate.parse(dateS.trim(), AppUtils.DATE_FORMAT);
                        List<String> m = new ArrayList<>(new ArrayList<>(Arrays.asList(meds.split(","))));
                        Consultation c = new Consultation(consID.trim(), patSSN.trim(), diag.trim(), m, date);
                        controller2.add(c);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    break;
                case 3:
                    System.out.println("Give disease: ");
                    String diag2 = keyboard.next();
                    try {
                        System.out.println(controller2.getPatientsWithDisease2(diag2.trim()));
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

    @Deprecated
    public void run() {
        System.out.println(menu());
        while (true) {
            int cmd = keyboard.nextInt();
            switch (cmd) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Give name: ");
                    String name = keyboard.next();
                    System.out.println("Give CNP: ");
                    String CNP = keyboard.next();
                    System.out.println("Give address: ");
                    String address = keyboard.nextLine();
                    Patient p = new Patient(name, CNP, address);
                    controller.addPatient(p);
                    break;
                case 2:

                    System.out.println("Give consID: ");
                    String consID = keyboard.nextLine();
                    System.out.println("Give SSN: ");
                    String patSSN = keyboard.nextLine();
                    System.out.println("Give diagnostic: ");
                    String diag = keyboard.nextLine();
                    System.out.println("Give meds: ");
                    String meds = keyboard.nextLine();
                    System.out.println("Give date: ");
                    String date = keyboard.nextLine();
                    List<String> m = new ArrayList<>(new ArrayList<>(Arrays.asList(meds.split(","))));
                    Consultation c = new Consultation(consID, patSSN, diag, m, date);
                    controller.addConsultation(c);
                    break;
                case 3:
                    System.out.println("Give disease: ");
                    String dias = keyboard.nextLine();
                    System.out.println(controller.getPatientsWithDisease(dias));
                    break;
                default:
                    System.out.println("Wrong command. Please retry: ");
                    break;
            }
        }

    }


}
