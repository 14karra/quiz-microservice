package quiz.controllerAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import quiz.controller.CorrectAnswerController;

@ControllerAdvice(assignableTypes = {CorrectAnswerController.class})
public class CorrectAnswerExceptionHandler extends BaseExceptionHandler {
}
