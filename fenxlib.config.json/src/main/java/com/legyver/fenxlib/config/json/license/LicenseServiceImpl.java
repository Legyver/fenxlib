package com.legyver.fenxlib.config.json.license;

import com.legyver.core.license.LicenseService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load the license information of open source libraries used by this module
 */
public class LicenseServiceImpl implements LicenseService {

    @Override
    public Properties loadLicenseProperties() throws IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = LicenseServiceImpl.class.getResourceAsStream("license.properties")) {
            properties.load(inputStream);
        }
        return properties;
    }
}
