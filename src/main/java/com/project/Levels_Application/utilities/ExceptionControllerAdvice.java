package com.project.Levels_Application.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(LevelsServiceException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(LevelsServiceException exception) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(600);
        error.setErrorMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.PRECONDITION_FAILED);
    }
}
