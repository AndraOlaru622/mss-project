package com.example.demo.exception;

public class DuplicateUserException extends RuntimeException {
    public DuplicateUserException() {
        super("There is already a user with the same title, publisher and publishing year");
    }
}