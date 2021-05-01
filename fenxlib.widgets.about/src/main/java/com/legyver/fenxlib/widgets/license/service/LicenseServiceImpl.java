package com.legyver.fenxlib.widgets.license.service;

import com.legyver.core.license.LicenseService;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Service to provide any licenses required by this library
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
