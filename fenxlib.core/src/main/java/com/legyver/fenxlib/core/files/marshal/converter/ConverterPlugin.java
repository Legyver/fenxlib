package com.legyver.fenxlib.core.files.marshal.converter;

import com.legyver.core.exception.CoreException;

/**
 * Converter that converts an Object into the appropriate format for saving
 * @param <T> the type of the format to be converted
 */
public interface ConverterPlugin<T> {
    /**
     * Convert an object into another type
     * @param object the object to be converted
     * @return the converted value
     * @throws CoreException if there is an error during conversion
     */
    T convert(Object object) throws CoreException;
}
