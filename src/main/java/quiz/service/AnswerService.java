package quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import quiz.entity.Answer;
import quiz.exception.AttemptToUpdateIdException;
import quiz.model.Response;
import quiz.repository.AnswerRepository;

import java.util.List;
import java.util.Set;

@Service
public class AnswerService {

    private static final Logger log = LoggerFactory.getLogger(AnswerService.class);
    private final AnswerRepository repository;

    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }

    public Answer saveAnswer(Answer newAnswer) {
        if (newAnswer.getId() != null) {
            throw new AttemptToUpdateIdException();
        }
        log.info("Saving a new answer. The new answer: {}", newAnswer);
        return repository.saveAndFlush(newAnswer);
    }

    public List<Answer> getAllAnswers() {
        log.info("Getting all quizzes");
        return repository.findAll();
    }

    public Set<Answer> getParticipantAnswers(Long questionId, Long participantId) {
        return repository.findParticipantAnswers(questionId, participantId);
    }

    public void deleteAnswer(Long id) {
        log.info("Deleting answer with ID={}", id);
        repository.deleteById(id);
        new Response(HttpStatus.OK.value(), "Answer with ID=" + id + " has been deleted.");
    }
}
