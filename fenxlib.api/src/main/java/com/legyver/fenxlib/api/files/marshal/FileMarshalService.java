package com.legyver.fenxlib.api.files.marshal;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.io.content.OutputContentWrapper;
import com.legyver.fenxlib.api.service.OrderedService;

/**
 * A Marshaller responsible for converting an object into the appropriate format for saving to file
 */
public interface FileMarshalService<T extends OutputContentWrapper> extends OrderedService<FileMarshalService> {
    /**
     * Convert an object into the appropriate format and save to file
     * @param content the content to be saved
     * @param fileOptions options describing the file to be saved
     * @return wrapper for the converted (marshalled) content to output to the file
     * @throws CoreException if there is an error during conversion or during saving the file
     */
    T marshal(Object content, FileOptions fileOptions) throws CoreException;
}
