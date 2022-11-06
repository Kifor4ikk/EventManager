package ru.kifor4ik.Controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.type.jakartajson.JakartaJsonIntegration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.kifor4ik.exception.ExceptionDto;
import ru.kifor4ik.exception.NotFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto badRequest(ConstraintViolationException exception, WebRequest request){
        return new ExceptionDto(HttpStatus.BAD_REQUEST.value(), exception.getConstraintViolations()
                .stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()).toString());
    }

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto notFound(NotFoundException exception, WebRequest request){
        return new ExceptionDto(HttpStatus.NOT_FOUND.value(), exception.getLocalizedMessage());
    }
}
