package quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import quiz.exception.QuestionNotFoundException;
import quiz.model.Response;


@ControllerAdvice(assignableTypes = {QuestionController.class})
public class QuestionExceptionHandler extends BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(QuestionNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Response questionNotFoundHandler(QuestionNotFoundException ex) {
        return new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}