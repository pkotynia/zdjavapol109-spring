package pl.sda.demo.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

//this annotation allows us to have one global place for our ExceptionHandlers
@RestControllerAdvice
public class ExceptionsHandler {

    //simple exception handler that just set http status and gets message form exception object
    @ExceptionHandler(ObjectNotFoundException.class)
    //ProblemDetails object allows us to return the response in standard json format
    public ProblemDetail handleObjectNotFoundException(ObjectNotFoundException exception){
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintsViolation(ConstraintViolationException exception){
        return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(400), getMessage(exception));
    }

    private static String getMessage(ConstraintViolationException exception) {
        return exception.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .collect(Collectors.joining("\n"));
    }
}
