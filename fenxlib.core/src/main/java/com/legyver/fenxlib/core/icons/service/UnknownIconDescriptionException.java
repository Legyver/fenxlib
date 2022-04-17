package com.legyver.fenxlib.core.icons.service;

import com.legyver.core.exception.CoreException;

/**
 * An exception thrown when the FontMap does not contain the described icon
 */
public class UnknownIconDescriptionException extends CoreException {

    /**
     * Construct an exception with a message
     * @param message the message
     */
    public UnknownIconDescriptionException(String message) {
        super(message);
    }
}
