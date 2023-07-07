package com.loy.security.exception;

public class IncorrectUserOrPassword extends RuntimeException {
    public IncorrectUserOrPassword(String message, Exception e){
        super(message);
    }
}
