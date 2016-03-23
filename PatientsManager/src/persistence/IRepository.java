package persistence;

import exception.PatientsManagerException;
import model.AppObjectTypes;
import model.Consultation;
import model.IAppElement;
import model.Patient;

import java.io.*;
import java.util.List;

/**
 * Created by Raul Breje on 03/23/2016.
 */
public interface IRepository {

    void cleanFiles2() throws PatientsManagerException;

    List<String> load(AppObjectTypes type) throws PatientsManagerException;

    List<Patient> getPatients() throws PatientsManagerException;

    List<Consultation> getConsultations() throws PatientsManagerException;

    void save(AppObjectTypes type, IAppElement elem) throws PatientsManagerException;

    @Deprecated
    void cleanFiles();

    @Deprecated
    String[] getPatientsFromFile() throws IOException;

    @Deprecated
    String[] getConsultationsFromFile() throws IOException;

    @Deprecated
    List<Patient> getPatientList();

    @Deprecated
    List<Consultation> getConsultationList();

    @Deprecated
    void savePatientToFile(Patient p) throws IOException;

    @Deprecated
    void saveConsultationToFile(Consultation c) throws IOException;


}
