package quiz.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import quiz.exception.*;
import quiz.model.Response;

@ControllerAdvice
public class BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(AttemptToUpdateIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Response attemptToUpdateIdHandler(AttemptToUpdateIdException ex) {
        return new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(DataMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Response dataMismatchExceptionHandler(DataMismatchException ex) {
        return new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler({UniqueConstraintException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    Response uniqueConstraintExceptionHandler(UniqueConstraintException ex) {
        return new Response(HttpStatus.CONFLICT.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NonexistentFKDataException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Response nonexistentFKDataExceptionHandler(NonexistentFKDataException ex) {
        return new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(FieldAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Response fieldAccessExceptionHandler(FieldAccessException ex) {
        return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
