package com.pei.exception;

public class AccountException extends MyUserException{
    public AccountException() {
        super();
    }

    public AccountException(String message) {
        super(message);
    }
}
