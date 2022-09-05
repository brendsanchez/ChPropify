package com.propify.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class GeneralException extends Exception{
    private static final long serialVersionUID = -6609322174316874160L;
    private final String message;
}
