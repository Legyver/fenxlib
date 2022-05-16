package com.legyver.fenxlib.core.files.marshal;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.service.OrderedService;

/**
 * A Marshaller responsible for converting an object into the appropriate format for saving to file
 */
public interface FileMarshal extends OrderedService<FileMarshal> {
    /**
     * Convert an object into the appropriate format and save to file
     * @param content the content to be saved
     * @param fileOptions options describing the file to be saved
     * @throws CoreException if there is an error during conversion or during saving the file
     */
    void marshal(Object content, FileOptions fileOptions) throws CoreException;
}
