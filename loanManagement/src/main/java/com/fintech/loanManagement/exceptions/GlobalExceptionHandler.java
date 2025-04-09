package com.fintech.loanManagement.exceptions;

import com.fintech.loanManagement.response.RestResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LoanException.class)
    public ResponseEntity<RestResponse> resourceNotFoundExceptionHandler(LoanException ex) {
        return new ResponseEntity<>(new RestResponse(ex.isSuccess(), ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new  ResponseEntity<>(new RestResponse(false,"IllegalArgumentException"), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestResponse> handleException(Exception ex) {
        return new ResponseEntity<>(new RestResponse(false, "something went wrong"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}

