package com.demo.urlshorterner.exception;

public class InvalidUrl extends RuntimeException{
    public InvalidUrl(String message) {
        super(message);
    }

    public InvalidUrl(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUrl(Throwable cause) {
        super(cause);
    }

    protected InvalidUrl(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
