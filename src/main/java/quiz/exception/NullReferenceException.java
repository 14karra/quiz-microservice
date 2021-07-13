package quiz.exception;

public class NullReferenceException extends RuntimeException {

    public NullReferenceException() {
        super("Detected a null reference.");
    }

    public NullReferenceException(String message) {
        super(message);
    }

    public NullReferenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullReferenceException(Throwable cause) {
        super(cause);
    }

    public NullReferenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
