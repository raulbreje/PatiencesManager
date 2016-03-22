package persistence;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;

import exception.PatientsManagerException;
import model.AppObjectTypes;
import model.Consultation;
import model.IAppElement;
import model.Patient;
import sun.rmi.transport.ObjectTable;
import util.AppUtils;


public class Repository {

    private String patients; // list of patients
    private String consultations; // list of consultation

    public Repository(String patients, String consultations) {
        this.patients = patients;
        this.consultations = consultations;
    }

    public void cleanFiles() throws PatientsManagerException {
        try {
            Files.deleteIfExists(Paths.get(patients));
            Files.deleteIfExists(Paths.get(consultations));
        } catch (IOException e) {
            throw new PatientsManagerException("IOException. Contact your administrator");
        }
    }

    public List<String> load(AppObjectTypes type) throws PatientsManagerException {
        switch (type) {
            case PATIENT:
                return getObjectFromFile(patients);
            case CONSULTATION:
                return getObjectFromFile(consultations);
            default:
                throw new PatientsManagerException("Type of object error. Contact your administrator");
        }
    }

    private List<String> getObjectFromFile(String fileName) throws PatientsManagerException {
        List<String> listOfPatients = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(listOfPatients::add);
        } catch (IOException e) {
            throw new PatientsManagerException("Wrong reading object. Contact your administrator");
        }
        return listOfPatients;
    }

    public List<Patient> getPatientList() throws PatientsManagerException {
        List<Patient> lp = new ArrayList<>();
        List<String> tokens = load(AppObjectTypes.PATIENT);
        for (String elem : tokens) {
            String[] pat = elem.split(",");
            lp.add(new Patient(pat[0].trim(), pat[1].trim(), pat[2].trim()));
        }
        return lp;
    }

    public List<Consultation> getConsultationList() throws PatientsManagerException {
        List<Consultation> lp = new ArrayList<>();
        List<String> tokens = load(AppObjectTypes.CONSULTATION);
        for (String elem : tokens) {
            List<String> med = new ArrayList<>();
            String[] pat = elem.split(",");
            String[] meds = pat[3].split("\\+");
            for (String m : meds) {
                med.add(m.trim());
            }
            LocalDate date = LocalDate.parse(pat[4].trim(), AppUtils.DATE_FORMAT);
            Consultation c = new Consultation(pat[0].trim(), pat[1].trim(), pat[2].trim(), med, date);
            lp.add(c);
        }
        return lp;
    }

    public void save(AppObjectTypes type, IAppElement elem) throws PatientsManagerException {
        switch (type) {
            case PATIENT:
                saveObjectToFile(patients, elem);
            case CONSULTATION:
                saveObjectToFile(consultations, elem);
            default:
                throw new PatientsManagerException("Type of object error. Contact your administrator");
        }
    }

    private void saveObjectToFile(String fileName, IAppElement elem) throws PatientsManagerException {
        if (elem instanceof Patient) {
            Patient p = (Patient) elem;
            List<Patient> lp = getPatientList();
            lp.add(p);
                FileWriter fw = new FileWriter(fileName);
                //@formatter:off
                Stream<Patient> lines = lp.stream();
                lines.forEach(package -> writeToFile(fw, package));
                //@formatter:on
                fw.close();
                lines.close();
            }
        } else if (elem instanceof Consultation) {
            Consultation c = (Consultation) elem;

        } else {
            throw new PatientsManagerException("Whatever. Contact your administrator.");
        }


    }

    public void savePatientToFile(Patient p) throws IOException        // save to file
    {


        int n = 0;
        BufferedReader in = new BufferedReader(new FileReader(patients));
        while ((in.readLine()) != null)
            n++;
        in.close();
        String[] sl = new String[n];
        String str;
        int i = 0;
        in = new BufferedReader(new FileReader(patients));
        while ((str = in.readLine()) != null) {
            sl[i] = str;
            i++;
        }
        in.close(); // append
        FileWriter fw = new FileWriter(patients);
        PrintWriter out = new PrintWriter(fw);
        for (i = 1; i < sl.length - 1; i++)
            out.println(sl[i]);
        out.println(p.toString());
        out.close();
    }

    public void saveConsultationToFile(Consultation c) throws IOException        // save to file
    {
        int n = 0;
        BufferedReader in = new BufferedReader(new FileReader(consultations));
        while ((in.readLine()) != null)
            n++;
        in.close();
        String[] sl = new String[n];
        String str;
        int i = 0;
        in = new BufferedReader(new FileReader(consultations));
        while ((str = in.readLine()) != null) {
            sl[i] = str;
            i++;
        }
        in.close(); // append
        FileWriter fw = new FileWriter(consultations);
        PrintWriter out = new PrintWriter(fw);
        for (i = 0; i < sl.length - 1; i++)
            out.println(sl[i]);
        out.println(c.toString());
        out.close();
    }

}
