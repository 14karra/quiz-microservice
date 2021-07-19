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

public class MAQuestionTypeImpl implements QuestionTypeExecutor {

    private final ParticipantAnswerRepository participantAnswerRepository;
    private final CorrectAnswerRepository correctAnswerRepository;
    private final PossibleAnswerRepository possibleAnswerRepository;

    public MAQuestionTypeImpl(ParticipantAnswerRepository participantAnswerRepository,
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
    public Response saveCorrectAnswer(CorrectAnswer correctAnswer) {
        correctAnswerRepository.saveAndFlush(correctAnswer);
        return new Response(HttpStatus.OK.value(), "Successfully saved!");
    }

    @Override
    public Response saveParticipantAnswer(ParticipantAnswer participantAnswer) {
        participantAnswerRepository.saveAndFlush(participantAnswer);
        return new Response(HttpStatus.OK.value(), "Successfully saved!");
    }
}
