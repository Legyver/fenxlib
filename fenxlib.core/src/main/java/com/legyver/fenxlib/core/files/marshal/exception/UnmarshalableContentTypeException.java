package com.legyver.fenxlib.core.files.marshal.exception;

/**
 * Exception thrown if there is no known marshaller for the defined content-type
 */
public class UnmarshalableContentTypeException extends UnmarshalableFileException {

    /**
     * Construct an exception with the specified message
     * @param message the message
     */
    public UnmarshalableContentTypeException(String message) {
        super(message);
    }
}
