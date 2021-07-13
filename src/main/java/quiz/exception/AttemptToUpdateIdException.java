package quiz.exception;

public class AttemptToUpdateIdException extends RuntimeException {

    public AttemptToUpdateIdException() {
        super("You cannot update the ID");
    }
}
