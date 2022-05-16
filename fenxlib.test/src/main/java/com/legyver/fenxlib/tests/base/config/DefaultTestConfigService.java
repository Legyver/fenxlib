package com.legyver.fenxlib.tests.base.config;

import com.legyver.fenxlib.api.config.adapter.IConfigAdapter;

import java.io.InputStream;

/**
 * Default test config.
 * Looks for the config first by path
 * - If path is not absolute, it will look for a directory corresponding to the package of this class
 * - If path is absolute, it will look for the absolute path in resources.
 * - If the resource can not be found by either method (input stream is null), then checks for a file in the 'config' directory in the root of the classpath
 *   ie: src/test/resources/config
 */
public class DefaultTestConfigService extends TestConfigService {
    @Override
    protected InputStream getTestResource(String filename) {
        InputStream inputStream = getClass().getResourceAsStream(filename);
        if (inputStream == null) {
            inputStream = getClass().getResourceAsStream("/config/" + filename);
        }
        return inputStream;
    }

}
