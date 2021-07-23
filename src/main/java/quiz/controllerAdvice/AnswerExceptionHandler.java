package quiz.controllerAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import quiz.controller.AnswerController;

@ControllerAdvice(assignableTypes = {AnswerController.class})
public class AnswerExceptionHandler extends BaseExceptionHandler {
}
