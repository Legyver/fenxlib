package com.legyver.fenxlib.api.files.marshal.exception;

import com.legyver.core.exception.CoreException;

/**
 * Exception thrown if there is a problem marshalling content to a file
 */
public class UnmarshalableFileException extends CoreException {

    /**
     * Construct an exception with a message
     * @param message the message
     */
    public UnmarshalableFileException(String message) {
        super(message);
    }
}
