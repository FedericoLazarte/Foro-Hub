package com.alura.forohub.infra.errors;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleBadRequest(MethodArgumentNotValidException e) {
        List<HandleValidationError> errors = e.getFieldErrors().stream()
                .map(HandleValidationError::new).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    private record HandleValidationError(String field, String error) {
        public HandleValidationError(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
