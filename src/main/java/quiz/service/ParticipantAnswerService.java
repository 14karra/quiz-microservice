package quiz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import quiz.config.QuestionTypeContext;
import quiz.entity.ParticipantAnswer;
import quiz.entity.Question;
import quiz.entity.QuestionType;
import quiz.exception.DataMismatchException;
import quiz.model.Response;

import java.util.Objects;
import java.util.Optional;

@Service
public class ParticipantAnswerService {

    private static final Logger log = LoggerFactory.getLogger(ParticipantAnswerService.class);
    private final QuestionTypeContext context;
    private final QuestionService questionService;

    public ParticipantAnswerService(QuestionTypeContext context,
                                    QuestionService questionService) {
        this.context = context;
        this.questionService = questionService;
    }

    public Response saveParticipantAnswer(QuestionType questionType, ParticipantAnswer newParticipantAnswer) {
        log.info("Saving a new participant answer: {}", newParticipantAnswer);
        Optional<Question> possibleQuestion = questionService.getQuestion(newParticipantAnswer.getCompositeKey().getQuestionId());
        if (possibleQuestion.filter(question -> Objects.equals(question.getType(), questionType)).isPresent()) {
            return context.get(questionType).saveParticipantAnswer(newParticipantAnswer);
        }
        throw new DataMismatchException(possibleQuestion.get().getType(), questionType);
    }
}
