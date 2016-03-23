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

import javax.xml.bind.annotation.XmlIDREF;


public class Repository implements IRepository {

    private String patients; // list of patients
    private String consultations; // list of consultation

    public Repository(String patients, String consultations) {
        this.patients = patients;
        this.consultations = consultations;
    }

    public void cleanFiles2() throws PatientsManagerException {
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

    public List<Patient> getPatients() throws PatientsManagerException {
        List<Patient> lp = new ArrayList<>();
        List<String> tokens = load(AppObjectTypes.PATIENT);
        for (String elem : tokens) {
            String[] pat = elem.split(",");
            lp.add(new Patient(pat[0].trim(), pat[1].trim(), pat[2].trim()));
        }
        return lp;
    }

    public List<Consultation> getConsultations() throws PatientsManagerException {
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
            List<String> lps = new ArrayList<>();
            lp.stream().map(String::valueOf).forEach(lps::add);
            try {
                Files.write(Paths.get(fileName), lps);
            } catch (IOException e) {
                throw new PatientsManagerException("Whatever. Contact your administrator.");
            }
        } else if (elem instanceof Consultation) {
            Consultation c = (Consultation) elem;
            List<Consultation> lc = getConsultationList();
            lc.add(c);
            List<String> lcs = new ArrayList<>();
            lc.stream().map(String::valueOf).forEach(lcs::add);
            try {
                Files.write(Paths.get(fileName), lcs);
            } catch (IOException e) {
                throw new PatientsManagerException("Whatever. Contact your administrator.");
            }
        } else {
            throw new PatientsManagerException("Whatever. Contact your administrator.");
        }


    }

    @Deprecated
    public void cleanFiles() {
        FileWriter fw;
        try {
            fw = new FileWriter(patients);
            PrintWriter out = new PrintWriter(fw);
            out.print("");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileWriter fwc;
        try {
            fwc = new FileWriter(consultations);
            PrintWriter out = new PrintWriter(fwc);
            out.print("");
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public String[] getPatientsFromFile() throws IOException {
        int n = 0;
        BufferedReader in = new BufferedReader(new FileReader(patients));
        while ((in.readLine()) != null) {
            n++;
        }
        in.close();

        String[] la = new String[n];
        String s = new String();
        int i = 0;
        in = new BufferedReader(new FileReader(patients));
        while ((s = in.readLine()) != null) {
            la[i] = s;
            i++;
        }
        in.close();
        return la;
    }

    @Deprecated
    public String[] getConsultationsFromFile() throws IOException {
        int n = 0;
        BufferedReader in = new BufferedReader(new FileReader(consultations));
        while ((in.readLine()) != null) {
            n++;
        }
        in.close();

        String[] la = new String[n];
        String s = new String();
        int i = 0;
        in = new BufferedReader(new FileReader(consultations));
        while ((s = in.readLine()) != null) {
            la[i] = s;
            i++;
        }
        in.close();
        return la;
    }

    @Deprecated
    public List<Patient> getPatientList() {
        List<Patient> lp = new ArrayList<Patient>();
        try {
            String[] tokens = getPatientsFromFile();

            String tok = new String();
            String[] pat;
            int i = 0;
            while (i < tokens.length) {
                tok = tokens[i];
                pat = tok.split(",");
                lp.add(new Patient(pat[0], pat[1], pat[2]));
                i = i + 1;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lp;
    }

    @Deprecated
    public List<Consultation> getConsultationList() {
        List<Consultation> lp = new ArrayList<Consultation>();
        try {
            String[] tokens = getConsultationsFromFile();

            String tok = new String();
            String[] cons;
            String[] meds;
            List<String> med = new ArrayList<String>();
            int i = 0;
            while (i < tokens.length) {
                tok = tokens[i];
                cons = tok.split(",");
                Consultation c = new Consultation(cons[0], cons[1], cons[2], med, cons[4]);
                meds = cons[3].split("\\+");
                for (int j = 0; j < meds.length - 1; j++) {
                    c.getMeds().add(meds[j]);
                }
                lp.add(c);
                i = i + 2;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lp;
    }

    @Deprecated
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

    @Deprecated
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
