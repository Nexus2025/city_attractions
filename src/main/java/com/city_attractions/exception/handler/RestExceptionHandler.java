package com.city_attractions.exception.handler;

import com.city_attractions.exception.DataNotFoundException;
import com.city_attractions.exception.DuplicateDataException;
import com.city_attractions.exception.IncorrectDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest req, DataNotFoundException e) {
        ErrorInfo info = new ErrorInfo(req.getRequestURI(), e.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(info, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest req, DuplicateDataException e) {
        ErrorInfo info = new ErrorInfo(req.getRequestURI(), e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(info, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(IncorrectDataException.class)
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest req, IncorrectDataException e) {
        ErrorInfo info = new ErrorInfo(req.getRequestURI(), e.getMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(info, HttpStatus.BAD_REQUEST);
    }
}