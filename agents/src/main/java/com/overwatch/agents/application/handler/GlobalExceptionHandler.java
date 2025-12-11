package com.overwatch.agents.application.handler;

import com.overwatch.agents.domain.exception.AgentNotFoundException;
import com.overwatch.agents.domain.exception.AgentTooYoungException;
import com.overwatch.agents.domain.exception.DirectorNotAuthorizedException;
import com.overwatch.agents.domain.exception.SuperNotFoundException;
import feign.FeignException;
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

    @ExceptionHandler(AgentTooYoungException.class)
    public ResponseEntity<ErrorResponse>handleAgentTooYoungException(AgentTooYoungException e){
        var errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), List.of(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY.value()).body(errorResponse);
    }

    @ExceptionHandler(AgentNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleAgentNotFoundException(AgentNotFoundException e){
        var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), List.of(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DirectorNotAuthorizedException.class)
    public ResponseEntity<ErrorResponse>handleDirectorNotAuthorizedException(DirectorNotAuthorizedException e){
        var errorResponse = new ErrorResponse(HttpStatus.FORBIDDEN.value(), e.getMessage(), List.of(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrorList =e.getFieldErrors();
        List<ApiFieldError> apiFieldErrors = fieldErrorList.stream().map(fieldError -> new ApiFieldError(fieldError.getField(),fieldError.getDefaultMessage())).toList();
        var errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation Error",apiFieldErrors,LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(SuperNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSuperNotFoundException( SuperNotFoundException e){
        var errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), List.of(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
