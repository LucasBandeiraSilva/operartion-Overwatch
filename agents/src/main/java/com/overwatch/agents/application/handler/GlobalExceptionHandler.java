package com.overwatch.agents.application.handler;

import com.overwatch.agents.domain.exception.AgentTooYoungException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
