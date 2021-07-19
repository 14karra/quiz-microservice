package quiz.exception;


import quiz.entity.CorrectAnswer;
import quiz.entity.ParticipantAnswer;
import quiz.entity.PossibleAnswer;
import quiz.entity.QuestionToQuiz;

public class InvalidPrimaryKeyFormatException extends RuntimeException {

    public InvalidPrimaryKeyFormatException(QuestionToQuiz.CompositeKey compositeKey) {
        super(prepareMessage(compositeKey));
    }

    public InvalidPrimaryKeyFormatException(ParticipantAnswer.CompositeKey compositeKey) {
        super(prepareMessage(compositeKey));
    }

    public InvalidPrimaryKeyFormatException(PossibleAnswer.CompositeKey compositeKey) {
        super(prepareMessage(compositeKey));
    }

    public InvalidPrimaryKeyFormatException(CorrectAnswer.CompositeKey compositeKey) {
        super(prepareMessage(compositeKey));
    }

    private static String prepareMessage(Object compositeKey) {
        return "Detected an invalid primary key format. The primary key: " + compositeKey.toString();
    }
}
