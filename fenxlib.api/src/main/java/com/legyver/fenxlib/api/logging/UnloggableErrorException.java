package com.legyver.fenxlib.api.logging;

/**
 * An exception throw when an error is received that can not be logged as the logging system is not instantiated yet
 */
public class UnloggableErrorException extends RuntimeException {

    /**
     * Construct an un-loggable exception
     * @param message the message
     * @param throwable the error
     */
    public UnloggableErrorException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
