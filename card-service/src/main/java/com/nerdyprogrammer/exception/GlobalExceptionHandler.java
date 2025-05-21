package com.nerdyprogrammer.exception;


import com.nerdyprogrammer.dto.ErrorResponseDto;
import com.nerdyprogrammer.dto.FieldError;
import com.nerdyprogrammer.dto.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardAlreadyExistsException.class)
    // argument in the exception handler indicates the type of exception
    public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistsException
            (CardAlreadyExistsException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    // argument in the exception handler indicates the type of exception
    public ResponseEntity<ErrorResponseDto> handleGlobalException
            (ResourceNotFoundException ex, WebRequest webRequest) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                LocalDateTime.now());
        return new ResponseEntity<>(errorResponseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**

     * @param request
     * @return all the validation errors in the request based on the validation criteria in the DTO objects
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<FieldError> errors = new ArrayList<>();
       ex.getBindingResult().getFieldErrors().forEach(
               error -> errors.add(new FieldError(error.getField(), error.getDefaultMessage())));
        Map<String, String> validationErrors = new HashMap<>();
        ValidationErrorResponse validationErrorResponse = buildErrorValidateErrorResponse(errors, request.getRequestURI());
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<FieldError> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
           String fieldName = extractFieldName(violation.getPropertyPath().toString());
            errors.add(new FieldError(fieldName, violation.getMessage()));
        }
        ValidationErrorResponse validationErrorResponse = buildErrorValidateErrorResponse(errors, request.getRequestURI());
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private String extractFieldName(String path) {
        if (path.contains(".")) {
            return path.substring(path.lastIndexOf(".") + 1);
        }
        return path;
    }

    private ValidationErrorResponse buildErrorValidateErrorResponse(List<FieldError> errors, String uri) {
        ValidationErrorResponse errorResponse = new ValidationErrorResponse();
        errorResponse.setErrors(errors);
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.name());
        errorResponse.setErrorTime(LocalDateTime.now().toString());
        errorResponse.setApiPath(uri);
        return errorResponse;
    }

}
