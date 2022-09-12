package com.legyver.fenxlib.widgets.license;

import com.legyver.core.exception.CoreException;

/**
 * An exception thrown when an unknown license metadata type is parsed
 */
public class UnknownLicenseMetadataException extends CoreException {
    /**
     * Construct an Unknown License Metadata Exception
     * @param message the message for the exception
     */
    public UnknownLicenseMetadataException(String message) {
        super(message);
    }
}
