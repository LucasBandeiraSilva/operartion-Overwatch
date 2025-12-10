package com.overwatch.supers.application.handler;

import com.overwatch.supers.domain.exception.BusinessException;
import com.overwatch.supers.domain.exception.SuperNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException ( MethodArgumentNotValidException e ){
        List<FieldError>errorList = e.getFieldErrors();
        List<ApiFieldError> apiFieldErrors = errorList.stream().map(fieldError ->  new ApiFieldError(fieldError.getField(), fieldError.getDefaultMessage())).toList();
        var errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error",apiFieldErrors, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e){
        var errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(),List.of(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(SuperNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleSuperNotFoundException(SuperNotFoundException e){
        var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),e.getMessage(),List.of(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
