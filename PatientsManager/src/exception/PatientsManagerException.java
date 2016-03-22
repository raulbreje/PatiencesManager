package exception;

/**
 * Created by Raul Breje on 03/22/2016.
 */
public class PatientsManagerException extends Exception {

    public PatientsManagerException() {
        super();
    }

    public PatientsManagerException(String message) {
        super(message);
    }

    public PatientsManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
