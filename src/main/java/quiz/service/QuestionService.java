package quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import quiz.entity.Question;
import quiz.entity.QuestionToQuiz;
import quiz.entity.Quiz;
import quiz.exception.AttemptToUpdateIdException;
import quiz.exception.NonexistentFKDataException;
import quiz.exception.QuestionNotFoundException;
import quiz.model.Response;
import quiz.repository.QuestionRepository;

import java.util.Optional;

@Service
public class QuestionService {

    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);
    private final QuestionRepository repository;
    private final QuizService quizService;
    private final QuestionToQuizService questionToQuizService;

    public QuestionService(QuestionRepository repository,
                           QuizService quizService,
                           QuestionToQuizService questionToQuizService) {
        this.repository = repository;
        this.quizService = quizService;
        this.questionToQuizService = questionToQuizService;
    }

    public Optional<Question> getQuestion(Long id) {
        return repository.findById(id);
    }

    public Question saveQuestion(Long quizId, Question newQuestion) {
        log.info("Saving a new question. The new question: {}", newQuestion);
        if (!quizService.quizExists(quizId)) {
            throw new NonexistentFKDataException(QuestionToQuiz.class, Quiz.class, quizId);
        }
        if (newQuestion.getId() != null) {
            throw new AttemptToUpdateIdException();
        }
        Question question = repository.saveAndFlush(newQuestion);
        QuestionToQuiz newQuestionToQuiz = new QuestionToQuiz.Builder()
                .compositeKey(new QuestionToQuiz.CompositeKey(question.getId(), quizId))
                .build();
        questionToQuizService.assignQuestionToQuiz(newQuestionToQuiz);
        return question;
    }

    public Question updateQuestion(Long id, Question newQuestion) {
        if (newQuestion.getId() != null && !id.equals(newQuestion.getId())) {
            throw new AttemptToUpdateIdException();
        }
        return repository.findById(id).map(question -> {
            log.info("Updating the question with ID={}. New data: {}", id, newQuestion);
            newQuestion.setId(question.getId());
            newQuestion.setQuiz(question.getQuiz());
            return repository.save(newQuestion);
        }).orElseThrow(() -> {
            log.warn("Question with ID={} not found", id);
            return new QuestionNotFoundException(id);
        });
    }

    public Response deleteQuestion(Long id) {
        log.info("Deleting question with ID={}", id);
        repository.deleteById(id);
        return new Response(HttpStatus.OK.value(), "Question with ID=" + id + " has been deleted.");
    }
}
