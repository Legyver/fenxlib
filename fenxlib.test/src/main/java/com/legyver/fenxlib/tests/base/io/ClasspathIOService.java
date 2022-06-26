package com.legyver.fenxlib.tests.base.io;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.io.IOService;
import com.legyver.fenxlib.api.io.content.OutputContentWrapper;

import java.io.InputStream;

/**
 * Read a file from resource
 * Looks for the config first by path
 * - If path is not absolute, it will look for a directory corresponding to the package of this class
 * - If path is absolute, it will look for the absolute path in resources.
 * - If the resource can not be found by either method (input stream is null), then checks for a file in the 'config' directory in the root of the classpath
 *   ie: src/test/resources/config
 */
public class ClasspathIOService implements IOService {
    @Override
    public InputStream loadInputStream(String appName, String name, boolean relativeToApplicationHome) throws CoreException {
        InputStream inputStream = getClass().getResourceAsStream(name);
        if (inputStream == null) {
            inputStream = getClass().getResourceAsStream("/config/" + name);
        }
        return inputStream;
    }

    @Override
    public boolean save(OutputContentWrapper saveMe, String saveAsName, boolean relativeToApplicationHome) throws CoreException {
        return false;
    }

    @Override
    public int order() {
        return -10; //before the FileIOService in fenxlib.core
    }
}
