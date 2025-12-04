package com.overwatch.agents.domain.exception;

public class DirectorNotAuthorizedException extends RuntimeException {
    public DirectorNotAuthorizedException( String code ) {
        super("Agent: " + code + " is not authorized to perform this action!");
    }
}
