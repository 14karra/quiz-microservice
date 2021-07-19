package quiz.exception;

public class DataMismatchException extends RuntimeException {

    public DataMismatchException(Object expectedValue, Object actualValue) {
        super("Data mismatch. Expected " + expectedValue + ", but received " + actualValue);
    }
}
