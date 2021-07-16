package quiz.exception;

public class UniqueConstraintException extends RuntimeException {

    public UniqueConstraintException() {
        super("Unique constraint violation.");
    }
}
