package quiz.exception;


import quiz.entity.QuestionToQuiz;

public class QuestionToQuizNotFoundException extends RuntimeException {

    public QuestionToQuizNotFoundException(QuestionToQuiz.CompositeKey compositeKey) {
        super("Could not find questionToQuiz with COMPOSITE_KEY=" + compositeKey.toString());
    }
}
