package com.tekdev.pagila.pagilaweb.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

//@ControllerAdvice
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
	
	@ExceptionHandler (RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(
    		RuntimeException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", ex.getMessage() != null ? ex.getMessage() : "Invalid Request");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }	
}
