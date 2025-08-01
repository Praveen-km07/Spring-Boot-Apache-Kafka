package com.praveen.todo_management_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setErrorCode("RESOURCE_NOT_FOUND");
        errorDetails.setDetails(webRequest.getDescription(false));
        errorDetails.setErrorTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TodoAPIException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> handleTodoAPIException(TodoAPIException exception,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setErrorCode("USER ALREADY EXISTS");
        errorDetails.setDetails(webRequest.getDescription(false));
        errorDetails.setErrorTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

     @ExceptionHandler(Exception.class)
     @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handleGenericException(RuntimeException exception,WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setErrorCode("INTERNAL_SERVER_ERROR");
        errorDetails.setDetails(webRequest.getDescription(false));
        errorDetails.setErrorTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value=HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorDetails> handleBadCredentials(BadCredentialsException exception,WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(exception.getMessage());
        errorDetails.setDetails(webRequest.getDescription(false));
        errorDetails.setErrorTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

}
