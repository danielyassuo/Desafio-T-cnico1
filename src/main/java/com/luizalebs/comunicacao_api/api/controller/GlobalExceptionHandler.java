package com.luizalebs.comunicacao_api.api.controller;


import com.luizalebs.comunicacao_api.infraestructure.exceptions.ConflictException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ResourceNotFoundException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourcesNotFoundException (ResourceNotFoundException resourceNotFoundException){
        return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handlerConflictException (ConflictException conflictException){
        return new ResponseEntity<>(conflictException.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handlerUnauthorizedException (UnauthorizedException unauthorizedException){
        return new ResponseEntity<>(unauthorizedException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handlerIllegalArgumentException (IllegalArgumentException illegalArgumentException){
        return new ResponseEntity<>(illegalArgumentException.getMessage(), HttpStatus.UNAUTHORIZED);
    }


}
