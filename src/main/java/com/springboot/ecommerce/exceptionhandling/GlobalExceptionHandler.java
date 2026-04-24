package com.springboot.ecommerce.exceptionhandling;

import java.time.LocalDateTime;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.springboot.ecommerce.util.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    // 🔹 Password mismatch
    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatch(PasswordMismatchException ex) {

        log.warn("Password mismatch: {}", ex.getMessage());

        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 🔹 Duplicate account
    @ExceptionHandler(DuplicateAccountException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicateAccountException ex) {

        log.warn("Duplicate account: {}", ex.getMessage());

        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // 🔹 Not found
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(AccountNotFoundException ex) {

        log.warn("Account not found: {}", ex.getMessage());

        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 🔹 Inactive account
    @ExceptionHandler(InActiveAccountException.class)
    public ResponseEntity<ErrorResponse> handleInactive(InActiveAccountException ex) {

        log.warn("Inactive account: {}", ex.getMessage());

        return buildResponse(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    // 🔹 Invalid state
    @ExceptionHandler(InvalidAccountStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidState(InvalidAccountStateException ex) {

        log.warn("Invalid account state: {}", ex.getMessage());

        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // 🔹 Generic fallback (VERY IMPORTANT)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {

        log.error("Unexpected error occurred", ex);

        return buildResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // 🔹 Common builder method (removes duplication)
    private ResponseEntity<ErrorResponse> buildResponse(String message, HttpStatus status) {

        ErrorResponse error = new ErrorResponse(
                message,
                status.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(error, status);
    }
}