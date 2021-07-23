package quiz.controllerAdvice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import quiz.exception.UserNotFoundException;
import quiz.model.Response;

@ControllerAdvice
public class UserExceptionHandler extends BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Response userNotFoundHandler(UserNotFoundException ex) {
        return new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
