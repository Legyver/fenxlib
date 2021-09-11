package com.legyver.fenxlib.widgets.filetree.license;

import com.legyver.core.license.LicenseService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Supply license information for libraries used by this module
 *
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
