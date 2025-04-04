package me.belyakov.ntlbank.web;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import me.belyakov.ntlbank.exceptions.economy.CardNotFoundException;
import me.belyakov.ntlbank.exceptions.economy.WithdrawException;
import me.belyakov.ntlbank.exceptions.token.BadJWTException;
import me.belyakov.ntlbank.exceptions.token.ExpiredJWTException;
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
        jsonResponse.put("error", "Bad Request. Field '" + ex.getFieldErrors().get(0) + "' invalid.");
        jsonResponse.put("invalid_fields", ex.getFieldErrors().stream().map(FieldError::getField).toList());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, headers, status);
    }

    @ExceptionHandler(exception = UserNotFoundException.class)
    protected ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException userNotFoundException, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("status", HttpStatus.UNAUTHORIZED);
        jsonResponse.put("error", userNotFoundException.getMessage());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(exception = BadJWTException.class )
    protected ResponseEntity<Object> handleBadJwtExceptions(BadJWTException badJWTException, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("status", HttpStatus.BAD_REQUEST);
        jsonResponse.put("error", badJWTException.getMessage());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(exception = ExpiredJWTException.class )
    protected ResponseEntity<Object> handleExpiredJwtExceptions(ExpiredJWTException exception, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("status", HttpStatus.UNAUTHORIZED.value());
        jsonResponse.put("error", exception.getMessage());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(exception = JWTVerificationException.class)
    protected ResponseEntity<Object> handleJWTVerificationException(JWTVerificationException exception, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("status", HttpStatus.UNAUTHORIZED.value());
        jsonResponse.put("error", exception.getMessage());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(exception = WithdrawException.class)
    protected ResponseEntity<Object> handleWithdrawException(WithdrawException withdrawException, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("status", HttpStatus.BAD_REQUEST);
        jsonResponse.put("error", withdrawException.getMessage());
        jsonResponse.put("blocked_payment_sum", withdrawException.getSum());
        jsonResponse.put("allowed_payment_sum", withdrawException.getAllowedSum());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(exception = CardNotFoundException.class)
    protected ResponseEntity<Object> handleCardNotFoundException(CardNotFoundException cardNotFoundException, WebRequest request) {
        Map<String, Object> jsonResponse = new LinkedHashMap<>();
        jsonResponse.put("timestamp", LocalDateTime.now());
        jsonResponse.put("status", HttpStatus.BAD_REQUEST);
        jsonResponse.put("error", cardNotFoundException.getMessage());
        jsonResponse.put("path", request.getDescription(false).replace("uri=", ""));
        return new ResponseEntity<>(jsonResponse, HttpStatus.BAD_REQUEST);
    }
}