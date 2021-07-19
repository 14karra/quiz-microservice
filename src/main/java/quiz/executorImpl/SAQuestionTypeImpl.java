package quiz.executorImpl;

import org.springframework.http.HttpStatus;
import quiz.entity.CorrectAnswer;
import quiz.entity.ParticipantAnswer;
import quiz.entity.PossibleAnswer;
import quiz.executor.QuestionTypeExecutor;
import quiz.model.Response;
import quiz.repository.CorrectAnswerRepository;
import quiz.repository.ParticipantAnswerRepository;
import quiz.repository.PossibleAnswerRepository;

public class SAQuestionTypeImpl implements QuestionTypeExecutor {

    private final ParticipantAnswerRepository participantAnswerRepository;
    private final CorrectAnswerRepository correctAnswerRepository;
    private final PossibleAnswerRepository possibleAnswerRepository;

    public SAQuestionTypeImpl(ParticipantAnswerRepository participantAnswerRepository,
                              CorrectAnswerRepository correctAnswerRepository,
                              PossibleAnswerRepository possibleAnswerRepository) {
        this.participantAnswerRepository = participantAnswerRepository;
        this.correctAnswerRepository = correctAnswerRepository;
        this.possibleAnswerRepository = possibleAnswerRepository;
    }

    @Override
    public Response savePossibleAnswer(PossibleAnswer possibleAnswer) {
        possibleAnswerRepository.saveAndFlush(possibleAnswer);
        return new Response(HttpStatus.OK.value(), "Successfully saved!");
    }

    @Override
    public Response saveCorrectAnswer(CorrectAnswer newCorrectAnswer) {
        CorrectAnswer.CompositeKey compositeKey = newCorrectAnswer.getCompositeKey();
        correctAnswerRepository
                .findByCompositeKey_QuestionId(compositeKey.getQuestionId())
                .ifPresent(answer -> correctAnswerRepository.deleteById(answer.getCompositeKey()));
        correctAnswerRepository.saveAndFlush(newCorrectAnswer);
        return new Response(HttpStatus.OK.value(), "Successfully saved!");
    }

    @Override
    public Response saveParticipantAnswer(ParticipantAnswer newAnswer) {
        Long participantId = newAnswer.getCompositeKey().getParticipantId();
        Long questionId = newAnswer.getCompositeKey().getQuestionId();
        participantAnswerRepository
                .findByCompositeKey_ParticipantIdAndCompositeKey_QuestionId(participantId, questionId)
                .ifPresent(participantAnswer -> participantAnswerRepository.deleteById(participantAnswer.getCompositeKey()));
        participantAnswerRepository.saveAndFlush(newAnswer);
        return new Response(HttpStatus.OK.value(), "Successfully saved!");
    }
}
