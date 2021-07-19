package quiz.controllerAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import quiz.controller.ParticipantController;


@ControllerAdvice(assignableTypes = {ParticipantController.class})
public class ParticipantExceptionHandler extends BaseExceptionHandler {
}
