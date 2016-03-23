package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exception.PatientsManagerException;
import model.IAppElement;
import persistence.IRepository;
import persistence.Repository;

import model.Consultation;
import model.Patient;

public class doctorController implements IController {

    private List<Patient> patients;
    private List<Consultation> consultations;
    private IRepository repository;

    /**
     * Constructors
     */

    public doctorController(IRepository repository) throws PatientsManagerException {
        this.repository = repository;
        patients = repository.getPatients();
        consultations = repository.getConsultations();
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    public Patient getPatientBySSN2(String SSN) {
        for (Patient p : patients) {
            if (SSN.equals(p.getSSN())) {
                return p;
            }
        }
        return null;
    }

    public Consultation getConsultationByID(String ID) {
        for (Consultation c : consultations) {
            if (ID.equals(c.getConsID())) {
                return c;
            }
            return null;
        }
    }

    public IRepository getRepository2() {
        return repository;
    }

    public void add(IAppElement elem) throws PatientsManagerException {
        if (elem instanceof Patient) {
            addPatient2((Patient) elem);
        } else if (elem instanceof Consultation) {
            addConsultation2((Consultation) elem);
        } else {
            throw new PatientsManagerException("Whatever. Contact your administrator.");
        }
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
