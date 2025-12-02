package com.overwatch.agents.domain.exception;

public class AgentTooYoungException extends RuntimeException {
    public AgentTooYoungException( String message ) {
        super(message);
    }
}
