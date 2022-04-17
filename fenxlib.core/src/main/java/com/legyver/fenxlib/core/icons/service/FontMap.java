package com.legyver.fenxlib.core.icons.service;

/**
 * Convert a description into the appropriate code
 */
public interface FontMap {
    /**
     * Get the code for the given description
     * @param description the description to lookup
     * @return the code
     * @throws UnknownIconDescriptionException if the icon requested is unknown
     */
    String getCode(String description) throws UnknownIconDescriptionException;
}
