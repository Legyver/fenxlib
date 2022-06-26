package com.legyver.fenxlib.api.io;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.io.content.OutputContentWrapper;
import com.legyver.fenxlib.api.service.OrderedService;

import java.io.InputStream;

/**
 * Provide loading/saving file mechanisms.
 *
 * Known implementations
 * DiskFileIOService
 * ResourceFileIService
 */
public interface IOService extends OrderedService<IOService> {

    /**
     * Load an input stream from a file/resource
     *
     * @param appName the name of the application used to determine the application home
     * @param name the name of the file/resource
     * @param relativeToApplicationHome true if the name should be resolved relative to the application home directory
     * @return the input stream, or null if it does not exist
     * @throws CoreException if there is an error loading the input stream
     */
    InputStream loadInputStream(String appName, String name, boolean relativeToApplicationHome) throws CoreException;

    /**
     * Save content to a file.
     *
     * @param saveMe the wrapper providing the content to save.
     *             It is the responsibility of the caller to convert the content to the appropriate format beforehand.
     *               For example, if you want to save a file as .json, you must convert it to String before this can be done.
     * @param saveAsName the name to save it as
     * @param relativeToApplicationHome try if the name should be resolved relative to the application home directory
     * @return true if the file was saved, false otherwise
     * @throws CoreException if there is an error saving the file
     */
    boolean save(OutputContentWrapper saveMe, String saveAsName, boolean relativeToApplicationHome) throws CoreException;
}
