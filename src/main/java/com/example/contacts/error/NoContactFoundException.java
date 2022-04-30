package com.example.contacts.error;

public class NoContactFoundException extends RuntimeException {
    public NoContactFoundException() {
        super();
    }

    public NoContactFoundException(String message) {
        super(message);
    }

    public NoContactFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoContactFoundException(Throwable cause) {
        super(cause);
    }
}
