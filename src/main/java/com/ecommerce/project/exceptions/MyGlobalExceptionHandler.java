package com.ecommerce.project.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> myMethodArgumentValidException(MethodArgumentNotValidException e){
        Map<String,String> response = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(
                err -> {
                    String fieldName = ((FieldError)err).getField();
                    String message = err.getDefaultMessage();
                    response.put(fieldName,message);
                }

        );
        System.out.println();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFound e){
        String message = e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(APIException.class)
    public ResponseEntity<String> myAPIException(APIException e){
        String message = e.getMessage();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);

    }
}
