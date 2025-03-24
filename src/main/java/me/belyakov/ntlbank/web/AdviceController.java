package me.belyakov.ntlbank.web;

import me.belyakov.ntlbank.exceptions.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class AdviceController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("status", HttpStatus.BAD_REQUEST.value());
        jsonResponse.put("error", "Bad Request. Field '" + ex.getFieldErrors().getFirst().getField() + "' invalid.");
        jsonResponse.put("invalid_fields", ex.getFieldErrors().stream().map(FieldError::getField).toList());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, headers, status);
    }

    @ExceptionHandler(exception = UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(RuntimeException runtimeException, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("status", HttpStatus.UNAUTHORIZED);
        jsonResponse.put("error", runtimeException.getMessage());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, HttpStatus.UNAUTHORIZED);
    }
}