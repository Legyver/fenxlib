package com.legyver.fenxlib.api.files.marshal.exception;

/**
 * Exception thrown if there is no marshaller registered to handle the file extension
 */
public class UnmarshalableFileExtensionException extends UnmarshalableFileException {

    /**
     * Construct an exception with a message
     * @param message the message
     */
    public UnmarshalableFileExtensionException(String message) {
        super(message);
    }
}
