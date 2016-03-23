package exception;

/**
 * Created by Raul Breje on 03/23/2016.
 */
public class ValidatorException extends Exception{

    public ValidatorException() {
        super();
    }

    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
