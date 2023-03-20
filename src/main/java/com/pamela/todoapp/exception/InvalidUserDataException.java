package com.pamela.todoapp.exception;

public class InvalidUserDataException extends IllegalArgumentException {

    public InvalidUserDataException(String message) {
        super(message);
    }
}
