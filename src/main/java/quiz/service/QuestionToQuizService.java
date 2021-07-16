package quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import quiz.entity.QuestionToQuiz;
import quiz.exception.UniqueConstraintException;
import quiz.repository.QuestionToQuizRepository;

@Service
public class QuestionToQuizService {

    private static final Logger log = LoggerFactory.getLogger(QuestionToQuizService.class);
    private final QuestionToQuizRepository repository;

    public QuestionToQuizService(QuestionToQuizRepository repository) {
        this.repository = repository;
    }

    public void assignQuestionToQuiz(final QuestionToQuiz newQuestionToQuiz) {
        log.info("Saving a new questionToQuiz: {}", newQuestionToQuiz);
        final Long questionId = newQuestionToQuiz.getCompositeKey().getQuestionId();
        final Long quizId = newQuestionToQuiz.getCompositeKey().getQuizId();
        if (questionAlreadyAssigned(questionId)) {
            log.warn("Question with id={} already associated to quiz with id={}", questionId, quizId);
            throw new UniqueConstraintException();
        }
        repository.saveAndFlush(newQuestionToQuiz);
    }

    private boolean questionAlreadyAssigned(Long questionId) {
        return repository.existsQuestionToQuizByCompositeKey_QuestionId(questionId);
    }
}
