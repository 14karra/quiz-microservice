package quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import quiz.config.QuestionTypeContext;
import quiz.entity.CorrectAnswer;
import quiz.entity.Question;
import quiz.exception.DataMismatchException;
import quiz.model.Response;

import java.util.Objects;
import java.util.Optional;

@Service
public class CorrectAnswerService {

    private static final Logger log = LoggerFactory.getLogger(CorrectAnswerService.class);
    private final QuestionService questionService;
    private final QuestionTypeContext context;

    public CorrectAnswerService(QuestionService questionService, QuestionTypeContext context) {
        this.questionService = questionService;
        this.context = context;
    }

    public Response saveCorrectAnswer(CorrectAnswer newCorrectAnswer) {
        log.info("Saving a new correct answer: {}", newCorrectAnswer);
        Long questionId = newCorrectAnswer.getCompositeKey().getQuestionId();
        Long answerId = newCorrectAnswer.getCompositeKey().getAnswerId();
        Optional<Question> optionalQuestion =
                questionService.getQuestion(questionId)
                        .filter(question -> question.getPossibleAnswers().stream()
                                .anyMatch(answer ->
                                        Objects.equals(answer.getId(), answerId)
                                ));
        if (optionalQuestion.isPresent()) {
            return context.get(optionalQuestion.get().getType()).saveCorrectAnswer(newCorrectAnswer);
        } else {
            throw new DataMismatchException(null, newCorrectAnswer);
        }
    }
}
