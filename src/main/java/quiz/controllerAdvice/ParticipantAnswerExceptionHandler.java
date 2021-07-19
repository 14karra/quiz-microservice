package quiz.controllerAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import quiz.controller.ParticipantAnswerController;


@ControllerAdvice(assignableTypes = {ParticipantAnswerController.class})
public class ParticipantAnswerExceptionHandler extends BaseExceptionHandler {
}
