package com.overwatch.agents.domain.exception;

public class AgentNotFoundException extends RuntimeException {
    public AgentNotFoundException( String code ) {
        super("Agent not found: " + code);
    }
}
