package com.demo.urlshorterner.exception;


import com.demo.urlshorterner.utils.ResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {


    @ExceptionHandler
    public ResponseEntity<ResponseError> handleUrlException(InvalidUrl exception){
        ResponseError error = ResponseError
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .timeStamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    public ResponseEntity<ResponseError> handleException(RuntimeException exception){
        ResponseError error = ResponseError
                .builder()
                .message(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis())
                .build();

        exception.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }


}
