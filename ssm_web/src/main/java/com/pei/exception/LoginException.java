package com.pei.exception;

public class LoginException extends MyUserException {
    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }
}
