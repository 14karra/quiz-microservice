package quiz.exception;

public class FieldAccessException extends RuntimeException {

    public FieldAccessException(String fieldName) {
        super("Field " + fieldName + "access exception.");
    }
}
