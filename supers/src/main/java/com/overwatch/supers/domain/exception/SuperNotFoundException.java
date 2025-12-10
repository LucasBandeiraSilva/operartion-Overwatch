package com.overwatch.supers.domain.exception;

public class SuperNotFoundException extends RuntimeException {
    public SuperNotFoundException( String code ) {
        super("Super not found: " + code);
    }

    public SuperNotFoundException (Long id){
        super("Super not found: " + id);
    }
}
