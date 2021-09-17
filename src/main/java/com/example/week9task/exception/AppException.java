package com.example.week9task.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{
    private HttpStatus status;
    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
