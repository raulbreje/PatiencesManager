package controller;

import exception.PatientsManagerException;
import exception.ValidatorException;
import model.Consultation;
import model.IAppElement;
import model.Patient;
import persistence.IRepository;
import persistence.Repository;

import java.util.List;

/**
 * Created by Raul Breje on 03/23/2016.
 */
public interface IController {

    List<Patient> getPatients() throws PatientsManagerException;

    List<Consultation> getConsultations() throws PatientsManagerException;

    void setConsultations(List<Consultation> consultations);

    Patient getPatientBySSN2(String SSN);

    Consultation getConsultationByID(String ID);

    IRepository getRepository2();

    void add(IAppElement elem) throws PatientsManagerException, ValidatorException;

    List<Patient> getPatientsWithDisease2(String disease) throws PatientsManagerException;

    @Deprecated
    List<Patient> getPatientList();

    @Deprecated
    List<Consultation> getConsultationList();

    @Deprecated
    void setConsulationList(List<Consultation> consultationList);

    @Deprecated
    int getPatientBySSN(String SSN);

    @Deprecated
    int getConsByID(String ID);

    @Deprecated
    Repository getRepository();

    @Deprecated
    void addPatient(Patient p);

    @Deprecated
    void addConsultation(Consultation c);

    @Deprecated
    List<Patient> getPatientsWithDisease(String disease);

}
