package org.clickandcollect.webservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.exceptions.UnknownResourceException;
import org.clickandcollect.webservice.dtos.ApiError;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler({UnknownResourceException.class})
    public ResponseEntity<Object> unknownResource(Exception ex, WebRequest request) {
        log.warn("Catching {} for {}", ex.getClass(), ex.getMessage());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({DuplicateKeyException.class})
    public ResponseEntity<Object> uniqueConstraintException(Exception ex) {
        log.warn("Catching {} for {}", ex.getClass(), ex.getMessage());
        ApiError apiError = ApiError.builder()
                .status(HttpStatus.CONFLICT)
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> validationException(MethodArgumentNotValidException ex, WebRequest request) {
        log.warn("Catching {} for {}", ex.getClass(), ex.getMessage());
        Map<String, String> mapErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            mapErrors.put(error.getField(),error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            mapErrors.put(error.getObjectName(), error.getDefaultMessage());
        }

        ApiError apiError = ApiError.builder()
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getLocalizedMessage())
                .errors(mapErrors)
                .build();
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

//    @ExceptionHandler({HttpMessageNotReadableException.class})
//    public ResponseEntity<Object>

}
