package quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import quiz.entity.Quiz;
import quiz.entity.QuizState;
import quiz.exception.AttemptToUpdateIdException;
import quiz.exception.QuizNotFoundException;
import quiz.model.Response;
import quiz.repository.QuizRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;

@Service
public class QuizService {

    private static final Logger log = LoggerFactory.getLogger(QuizService.class);
    private final QuizRepository repository;

    public QuizService(QuizRepository repository) {
        this.repository = repository;
    }

    public boolean quizExists(Long id) {
        log.info("Checking if quiz with ID={} exists", id);
        boolean instanceExists = repository.existsById(id);
        if (instanceExists) {
            log.info("Quiz with ID={} was found", id);
        } else {
            log.warn("Quiz with ID={} not found", id);
        }
        return instanceExists;
    }

    public Quiz getQuiz(Long id) {
        log.info("Getting the quiz with ID={}", id);
        return repository.findById(id).orElseThrow(() -> {
            log.warn("Quiz with ID={} not found", id);
            return new QuizNotFoundException(id);
        });
    }

    public List<Quiz> getAllQuizzes() {
        log.info("Getting all quizzes");
        return repository.findAll();
    }

    public List<Quiz> getActiveQuizzes() {
        log.info("Getting active quizzes");
        return repository.findQuizByState(QuizState.ACTIVE);
    }

    public Quiz saveQuiz(Quiz newQuiz) {
        if (newQuiz.getId() != null) {
            throw new AttemptToUpdateIdException();
        }
        log.info("Saving a new quiz. The new quiz: {}", newQuiz);
        return repository.saveAndFlush(newQuiz);
    }

    public Quiz updateQuiz(Long id, Quiz newQuiz) {
        if (newQuiz.getId() != null && !id.equals(newQuiz.getId())) {
            throw new AttemptToUpdateIdException();
        }
        return repository.findById(id).map(quiz -> {
            log.info("Updating the quiz with ID={}. New data: {}", id, newQuiz);
            newQuiz.setId(quiz.getId());
            newQuiz.setCreationDate(quiz.getCreationDate());
            updateDates(newQuiz, quiz);
            return repository.save(newQuiz);
        }).orElseThrow(() -> {
            log.warn("Quiz with ID={} not found", id);
            return new QuizNotFoundException(id);
        });
    }

    private void updateDates(Quiz newQuiz, Quiz quiz) {
        if (!quiz.getState().equals(newQuiz.getState())) {
            if (newQuiz.getState().equals(QuizState.ACTIVE)) {
                newQuiz.setStartDate(OffsetDateTime.now());
                newQuiz.setEndDate(quiz.getEndDate());
            } else if (newQuiz.getState().equals(QuizState.ENDED)) {
                newQuiz.setEndDate(OffsetDateTime.now());
                newQuiz.setStartDate(quiz.getStartDate());
            }
        }
    }

    public Response deleteQuiz(Long id) {
        log.info("Deleting quiz with ID={}", id);
        repository.deleteById(id);
        return new Response(HttpStatus.OK.value(), "Quiz with ID=" + id + " has been deleted.");
    }

    public Set<Quiz> getQuizzesPassedByParticipant(Long participantId) {
        log.info("getting quizzes passed by the participant with ID={}", participantId);
        return repository.getQuizzesPassedByParticipant(participantId);
    }
}
