package quiz.exception;


import quiz.entity.QuestionToQuiz;

public class InvalidPrimaryKeyFormatException extends RuntimeException {

    public InvalidPrimaryKeyFormatException(QuestionToQuiz.CompositeKey compositeKey) {
        super("Detected an invalid primary key format. The primary key: " + compositeKey);
    }
}
