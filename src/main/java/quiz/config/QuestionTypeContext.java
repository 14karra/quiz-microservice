package quiz.config;

import org.springframework.stereotype.Component;
import quiz.entity.ParticipantAnswer;
import quiz.entity.QuestionType;
import quiz.executor.QuestionTypeExecutor;
import quiz.executorImpl.MAQuestionTypeImpl;
import quiz.executorImpl.OAQuestionTypeImpl;
import quiz.executorImpl.SAQuestionTypeImpl;
import quiz.repository.CorrectAnswerRepository;
import quiz.repository.ParticipantAnswerRepository;
import quiz.repository.PossibleAnswerRepository;
import quiz.service.AnswerService;

import java.util.HashMap;

@Component
public class QuestionTypeContext {

    private final ParticipantAnswerRepository participantAnswerRepository;
    private final CorrectAnswerRepository correctAnswerRepository;
    private final PossibleAnswerRepository possibleAnswerRepository;
    private final AnswerService answerService;
    private final HashMap<QuestionType, QuestionTypeExecutor> context = new HashMap<>();

    public QuestionTypeContext(ParticipantAnswerRepository participantAnswerRepository,
                               CorrectAnswerRepository correctAnswerRepository,
                               PossibleAnswerRepository possibleAnswerRepository, AnswerService answerService) {
        this.participantAnswerRepository = participantAnswerRepository;
        this.correctAnswerRepository = correctAnswerRepository;
        this.possibleAnswerRepository = possibleAnswerRepository;
        this.answerService = answerService;
        initiateQuestionTypeContext();
    }

    private void initiateQuestionTypeContext() {
        register(QuestionType.OPEN_ANSWER_QUESTION, new OAQuestionTypeImpl(participantAnswerRepository, answerService));
        register(QuestionType.SINGLE_ANSWER_QUESTION, new SAQuestionTypeImpl(participantAnswerRepository, correctAnswerRepository, possibleAnswerRepository));
        register(QuestionType.MULTIPLE_ANSWER_QUESTION, new MAQuestionTypeImpl(participantAnswerRepository, correctAnswerRepository, possibleAnswerRepository));
    }

    private void register(QuestionType type, QuestionTypeExecutor function) {
        context.put(type, function);
    }

    public Object call(QuestionType type, ParticipantAnswer participantAnswer) {
        return context.get(type).saveParticipantAnswer(participantAnswer);
    }

    public QuestionTypeExecutor get(QuestionType type) {
        return context.get(type);
    }
}
