package quiz.exception;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Long id) {
        super("Could not find question with ID=" + id);
    }
}
