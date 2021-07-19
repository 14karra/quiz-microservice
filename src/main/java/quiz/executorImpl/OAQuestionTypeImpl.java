package quiz.executorImpl;

import org.springframework.http.HttpStatus;
import quiz.entity.CorrectAnswer;
import quiz.entity.ParticipantAnswer;
import quiz.entity.PossibleAnswer;
import quiz.exception.DataMismatchException;
import quiz.executor.QuestionTypeExecutor;
import quiz.model.Response;
import quiz.repository.ParticipantAnswerRepository;
import quiz.service.AnswerService;

public class OAQuestionTypeImpl implements QuestionTypeExecutor {

    private final ParticipantAnswerRepository participantAnswerRepository;
    private final AnswerService answerService;

    public OAQuestionTypeImpl(ParticipantAnswerRepository participantAnswerRepository, AnswerService answerService) {
        this.participantAnswerRepository = participantAnswerRepository;
        this.answerService = answerService;
    }

    @Override
    public Response savePossibleAnswer(PossibleAnswer possibleAnswer) {
        throw new DataMismatchException(null, possibleAnswer);
    }

    @Override
    public Response saveCorrectAnswer(CorrectAnswer correctAnswer) {
        throw new DataMismatchException(null, correctAnswer);
    }

    @Override
    public Response saveParticipantAnswer(ParticipantAnswer newAnswer) {
        Long participantId = newAnswer.getCompositeKey().getParticipantId();
        Long questionId = newAnswer.getCompositeKey().getQuestionId();
        participantAnswerRepository
                .findByCompositeKey_ParticipantIdAndCompositeKey_QuestionId(participantId, questionId)
                .ifPresent(participantAnswer -> answerService.deleteAnswer(participantAnswer.getCompositeKey().getAnswerId()));
        participantAnswerRepository.saveAndFlush(newAnswer);
        return new Response(HttpStatus.OK.value(), "Successfully saved!");
    }
}
