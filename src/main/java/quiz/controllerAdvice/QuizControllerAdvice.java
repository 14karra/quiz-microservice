package quiz.controllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import quiz.decorator.AddParticipantAnswers;
import quiz.decorator.QuizControllerDecorator;
import quiz.entity.Answer;
import quiz.entity.Quiz;
import quiz.exception.FieldAccessException;
import quiz.service.AnswerService;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Set;

@ControllerAdvice(annotations = {QuizControllerDecorator.class})
public class QuizControllerAdvice implements ResponseBodyAdvice<Set<Quiz>> {

    private static final Logger log = LoggerFactory.getLogger(QuizControllerAdvice.class);
    private final AnswerService answerService;

    public QuizControllerAdvice(AnswerService answerService) {
        this.answerService = answerService;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(AddParticipantAnswers.class);
    }

    @Override
    public Set<Quiz> beforeBodyWrite(Set<Quiz> body, MethodParameter returnType, MediaType selectedContentType,
                                     Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                     ServerHttpRequest request, ServerHttpResponse response) {
        Long participantId = extractParticipantId(request.getURI().getPath());
        body.forEach(quiz -> quiz.getQuestions()
                .forEach(question -> {
                    try {
                        Set<Answer> participantAnswers = answerService.getParticipantAnswers(question.getId(), participantId);
                        Field participantAnswersField = question.getClass().getDeclaredField("participantAnswers");
                        participantAnswersField.setAccessible(true);
                        participantAnswersField.set(question, participantAnswers);
                    } catch (NoSuchFieldException | IllegalAccessException ex) {
                        log.warn(ex.getMessage(), ex);
                        throw new FieldAccessException("participantAnswers");
                    }
                }));
        return body;
    }

    private Long extractParticipantId(String uri) {
        if (uri.lastIndexOf('/') == uri.length() - 1) {
            uri = uri.substring(0, uri.length() - 1);
        }
        int lastSlashIndex = uri.trim().lastIndexOf('/');
        return Long.valueOf(uri.substring(lastSlashIndex + 1));
    }
}
