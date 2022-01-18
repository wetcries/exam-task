package com.app1c.chat.exceptions;

public class ClientProblemsException extends RuntimeException{
    public ClientProblemsException() {
        super();
    }

    public ClientProblemsException(String message) {
        super(message);
    }

    public ClientProblemsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientProblemsException(Throwable cause) {
        super(cause);
    }

    protected ClientProblemsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
