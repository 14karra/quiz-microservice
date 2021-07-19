package quiz.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import quiz.controller.QuizController;
import quiz.exception.QuizNotFoundException;
import quiz.model.Response;


@ControllerAdvice(assignableTypes = {QuizController.class})
public class QuizExceptionHandler extends BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(QuizNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    quiz.model.Response quizNotFoundHandler(QuizNotFoundException ex) {
        return new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
