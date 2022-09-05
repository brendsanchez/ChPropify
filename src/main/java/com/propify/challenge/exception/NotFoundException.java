package com.propify.challenge.exception;

public class NotFoundException extends GeneralException{
    private static final long serialVersionUID = 4921742607685773480L;

    public NotFoundException(String message) {
        super(message);
    }
}
