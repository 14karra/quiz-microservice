package quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import quiz.exception.AttemptToUpdateIdException;
import quiz.exception.QuizNotFoundException;
import quiz.model.Response;


@ControllerAdvice
public class QuizExceptionHandler {

    @ResponseBody
    @ExceptionHandler(QuizNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    quiz.model.Response quizNotFoundHandler(QuizNotFoundException ex) {
        return new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(AttemptToUpdateIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Response attemptToUpdateIdHandler(AttemptToUpdateIdException ex) {
        return new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }
}