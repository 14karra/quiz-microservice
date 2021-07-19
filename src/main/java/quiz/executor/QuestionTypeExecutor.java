package quiz.executor;

import quiz.entity.CorrectAnswer;
import quiz.entity.ParticipantAnswer;
import quiz.entity.PossibleAnswer;
import quiz.model.Response;

public interface QuestionTypeExecutor {

    Response saveParticipantAnswer(ParticipantAnswer participantAnswer);

    Response saveCorrectAnswer(CorrectAnswer correctAnswer);

    Response savePossibleAnswer(PossibleAnswer possibleAnswer);
}
