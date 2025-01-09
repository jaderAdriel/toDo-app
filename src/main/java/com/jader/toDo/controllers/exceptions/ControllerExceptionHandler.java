package com.jader.toDo.controllers.exceptions;

import com.jader.toDo.services.exceptions.DuplicateResourceException;
import com.jader.toDo.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handleValidationException(MethodArgumentNotValidException e) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(fieldError -> {
            String fieldName = fieldError.getField();          // Nome do campo (ex.: "name")
            String errorMessage = fieldError.getDefaultMessage(); // Mensagem de erro (ex.: "Name is mandatory")
            errors.put(fieldName, errorMessage);
        });

        StandardError err = new StandardError(Instant.now(), status.value(), "Validation error", errors.toString(), null);

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<StandardError> handleDuplicateResource(DuplicateResourceException e, HttpServletRequest request) {
        StandardError error = new StandardError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Duplicate Resource",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
