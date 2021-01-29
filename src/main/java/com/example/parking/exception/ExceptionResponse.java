package com.example.parking.exception;

import java.io.Serializable;

public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 3620491867032462290L;

    /**
     * custom message
     */
    private final String message;

    /**
     * custom title
     */
    private final String title;

    /**
     * message from the exception
     */
    private String exceptionMessage;

    /**
     * type of the exception
     */
    private Class<?> exceptionType;

    public ExceptionResponse(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public ExceptionResponse(String title, String message, Exception exceptionSource) {
        this(title, message);
        if (exceptionSource != null) {
            this.exceptionMessage = exceptionSource.getMessage();
            this.exceptionType = exceptionSource.getClass();
        }
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public Class<?> getExceptionType() {
        return exceptionType;
    }
}
