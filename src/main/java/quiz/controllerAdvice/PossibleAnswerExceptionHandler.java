package quiz.controllerAdvice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import quiz.controller.PossibleAnswerController;


@ControllerAdvice(assignableTypes = {PossibleAnswerController.class})
public class PossibleAnswerExceptionHandler extends BaseExceptionHandler {
}
