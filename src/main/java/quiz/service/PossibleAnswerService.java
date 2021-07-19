package quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import quiz.context.QuestionTypeContext;
import quiz.entity.PossibleAnswer;
import quiz.entity.Question;
import quiz.exception.DataMismatchException;
import quiz.model.Response;

@Service
public class PossibleAnswerService {

    private static final Logger log = LoggerFactory.getLogger(PossibleAnswerService.class);
    private final QuestionTypeContext context;
    private final QuestionService questionService;

    public PossibleAnswerService(QuestionTypeContext context, QuestionService questionService) {
        this.context = context;
        this.questionService = questionService;
    }

    public Response savePossibleAnswer(PossibleAnswer newPossibleAnswer) {
        try {
            log.info("Saving a new possible answer: {}", newPossibleAnswer);
            Long questionId = newPossibleAnswer.getCompositeKey().getQuestionId();
            Question existingQuestion = questionService
                    .getQuestion(questionId)
                    .orElseThrow(() -> new DataMismatchException(null, newPossibleAnswer));
            return context.get(existingQuestion.getType()).savePossibleAnswer(newPossibleAnswer);
        } catch (DataMismatchException ex) {
            log.warn(ex.getMessage(), ex);
            throw ex;
        }
    }
}
