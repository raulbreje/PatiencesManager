package controller;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

import exception.PatientsManagerException;
import exception.ValidatorException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.*;
import persistence.IRepository;
import persistence.Repository;
import util.AppUtils;

public class doctorController implements IController {

    private List<Patient> patients;
    private List<Consultation> consultations;
    private IRepository repository;

    public doctorController(IRepository repository) {
        this.repository = repository;
    }

    public List<Patient> getPatients() throws PatientsManagerException {
        if (patients == null) {
            patients = repository.getPatients();
        }
        return patients;
    }

    public List<Consultation> getConsultations() throws PatientsManagerException {
        if (consultations == null) {
            consultations = repository.getConsultations();
        }
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public Patient getPatientBySSN2(String SSN) throws PatientsManagerException {
        for (Patient p : getPatients()) {
            if (SSN.equals(p.getSSN())) {
                return p;
            }
        }
        return null;
    }

    public Consultation getConsultationByID(String ID) {
//        Consultation c = consultations.forEach((t) -> { ; return t; });
        for (Consultation c : consultations) {
            if (ID.equals(c.getConsID())) {
                return c;
            }
        }
        return null;
    }

    public IRepository getRepository2() {
        return repository;
    }

    public void add(IAppElement elem) throws PatientsManagerException, ValidatorException {
        if (elem instanceof Patient) {
            addPatient2((Patient) elem);
        } else if (elem instanceof Consultation) {
            addConsultation2((Consultation) elem);
        } else {
            throw new PatientsManagerException("Whatever. Contact your administrator.");
        }
    }

    private void addPatient2(Patient p) throws ValidatorException, PatientsManagerException {
        Validator.validatePerson(p);
        if (getPatientBySSN2(p.getSSN()) == null) {
            patients.add(p);
            repository.save(AppObjectTypes.PATIENT, p);
        } else {
            throw new PatientsManagerException("Patient don't exists. Contact your administrator.");
        }
    }

    private void addConsultation2(Consultation c) throws PatientsManagerException, ValidatorException {
        Validator.validateConsultation(c);
        if (getPatientBySSN2(c.getPatientSSN()) != null && getConsultationByID(c.getConsID()) == null) {
            consultations.add(c);
            repository.save(AppObjectTypes.CONSULTATION, c);
            Patient p = getPatientBySSN2(c.getPatientSSN());
            p.setConsNum(p.getConsNum() + 1);
        } else {
            throw new PatientsManagerException("Consultation ID already exists. Contact your administrator.");
        }
    }

    public List<Patient> getPatientsWithDisease2(String disease) throws PatientsManagerException {
        List<Consultation> cs = getConsultations();
        List<Patient> pat = new ArrayList<>();
        List<Patient> sortedPat = new ArrayList<>();
        if (!AppUtils.isNull(disease)) {
            cs.stream().filter(c -> c.getDiag().toLowerCase().contains(disease.toLowerCase())).forEach(c -> {
                Patient p = null;
                try {
                    p = getPatientBySSN2(c.getPatientSSN());
                } catch (PatientsManagerException e) {
                    e.printStackTrace();
                }
                if (!pat.contains(p)) {
                    pat.add(p);
                }
            });
        } else {
            throw new PatientsManagerException("Disease null. Contact your administrator.");
        }
        Comparator<Patient> byConsNumber = (p1, p2) -> Integer.compare(
                p1.getConsNum(), p2.getConsNum());
        pat.stream().sorted(byConsNumber)
                .forEach(sp -> sortedPat.add(sp));
        return sortedPat;
    }

    @Deprecated
    private List<Patient> PatientList;
    @Deprecated
    private List<Consultation> ConsultationList;
    @Deprecated
    private Repository rep;

    @Deprecated
    public doctorController(Repository rep) {
        this.rep = rep;
        this.PatientList = rep.getPatientList();
        this.ConsultationList = rep.getConsultationList();
        // Get list from file in order to avoid duplicates.
    }

    /**
     * Getters
     */
    @Deprecated
    public List<Patient> getPatientList() {
        return PatientList;
    }

    @Deprecated
    public List<Consultation> getConsultationList() {
        return ConsultationList;
    }

    @Deprecated
    public void setConsulationList(List<Consultation> consultationList) {
        ConsultationList = consultationList;
    }

    @Deprecated
    public int getPatientBySSN(String SSN) {
        for (int i = 0; i < PatientList.size(); i++) {
            if (PatientList.get(i).getSSN().equals(SSN))
                return i;
        }

        return -1;
    }

    @Deprecated
    public int getConsByID(String ID) {
        for (int i = 0; i < ConsultationList.size(); i++) {
            if (ConsultationList.get(i).getConsID().compareTo(ID) == 0) {
            /*	System.out.println("I proud to have found " + ID + " here: " + i);
                System.out.println("Proof : " + ConsultationList.get(i).toString()); */
                return i - 1;
            }
        }

        return -1;
    }

    @Deprecated
    public Repository getRepository() {
        return rep;
    }


    /**
     * Others
     */
    @Deprecated
    public void addPatient(Patient p) //throws Exception
    {
        if (p.getName() != null && p.getSSN() != null && p.getAddress() != null &&
                p.getSSN().matches(("[0-9]*")) && p.getName().matches("^[A-Za-z ']*$") &&
                getPatientBySSN(p.getSSN()) == -1) {
            PatientList.add(p);
            try {
                rep.savePatientToFile(p);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // adding of a new consultation for a patient (consultation date, diagnostic, prescription drugs)

    @Deprecated
    public void addConsultation(Consultation c) {
        if (c.getMeds() == null)
            return;

        if (c.getConsID() != null && c.getPatientSSN() != null && c.getDiag() != null &&
                c.getMeds().size() != 0 && this.getPatientBySSN(c.getPatientSSN()) > -1
                && this.getConsByID(c.getConsID()) == -1) {
            ConsultationList.add(c);
            try {
                rep.saveConsultationToFile(c);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Patient p = new Patient();
            p = this.getPatientList().get(this.getPatientBySSN(c.getPatientSSN()));
            p.setConsNum(p.getConsNum() + 1);
        }

        //else System.out.println(c.getConsID() + " " + this.getPatientBySSN(c.getPatientSSN()) + " " + this.getConsByID(c.getConsID()));
    }

    @Deprecated
    public List<Patient> getPatientsWithDisease(String disease) {
        List<Consultation> c = this.getConsultationList();
        List<Patient> p = new ArrayList<Patient>();
        if (disease != null) {
            int chk = 1;

            for (int i = 0; i < c.size() - 1; i++) {
                if (c.get(i).getDiag().toLowerCase().contains(disease.toLowerCase())) // so that it is case insensitive
                {
                    for (int j = 0; j < p.size() - 1; j++) // verify patient was not already added
                    {
                        if (p.get(j).getSSN().equals(c.get(i).getPatientSSN())) {
                            chk = p.get(j).getConsNum();
                        }
                    }

                    if (chk == 1) {
                        p.add(this.getPatientList().get(this.getPatientBySSN(c.get(i).getPatientSSN()))); // get model.Patient by SSN
                    }
                    chk = 1;
                }
            }

            // Sort the list

            Patient paux = new Patient();

            for (int i = 0; i < p.size(); i++)
                for (int j = i + 1; j < p.size() - 1; j++)
                    if (p.get(j - 1).getConsNum() < p.get(j).getConsNum()) {
                        paux = p.get(j - 1);
                        p.set(j - 1, p.get(j));
                        p.set(j, paux);
                    }
        }
        return p;
    }


	/* For debugging purposes
    public void printList()
	{
		for (int i = 0; i < PatientList.size(); i++)
		{
			System.out.println(PatientList.get(i).toString());
		}
	} */


}
