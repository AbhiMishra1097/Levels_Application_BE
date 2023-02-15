package com.project.Levels_Application.utilities;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("timestamp", new Date());
        responseBody.put("statusCode", status.value());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<String> errorList = new ArrayList<>();

        for(FieldError fe : fieldErrors){
            String errorMessage = fe.getDefaultMessage();
            errorList.add(errorMessage);
        }
//        fieldErrors.stream().map(fieldError -> {
//            String errorMessage = fieldError.getDefaultMessage();
//            errorList.add(errorMessage);
//            return null;
//        });

        responseBody.put("errors", errorList);
        return new ResponseEntity<>(responseBody, headers, status);
    }

}
