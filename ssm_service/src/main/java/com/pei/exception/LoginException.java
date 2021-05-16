package com.pei.exception;

public class LoginException extends MyUserException{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
