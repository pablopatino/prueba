package com.example.accenture.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExcepcionHandler {


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> badRequestException(Exception e){
       Error error = new Error(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> notFoundException(Exception e){
        Error error = new Error(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
