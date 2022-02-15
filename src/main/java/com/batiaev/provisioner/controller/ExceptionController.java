package com.batiaev.provisioner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<String> exception(IllegalArgumentException exception) {
        return new ResponseEntity<String>("invalid argument: " + exception.getMessage(), HttpStatus.BAD_REQUEST);//FIXME
    }
}
