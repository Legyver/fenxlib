package com.legyver.fenxlib.widgets.license.service;

import com.legyver.core.license.LicenseService;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;

public class LicenseServiceRegistry {
	private final ServiceLoader<LicenseService> licenseServiceLoader;
	private static LicenseServiceRegistry instance;

	private LicenseServiceRegistry() {
		licenseServiceLoader = ServiceLoader.load(LicenseService.class);
	}

	public static LicenseServiceRegistry getInstance() {
		if (instance == null) {
			synchronized (LicenseServiceRegistry.class) {
				if (instance == null) {
					instance = new LicenseServiceRegistry();
				}
			}
		}
		return instance;
	}

	public Properties loadLicenseProperties() throws IOException {
		Properties union = new Properties();
		for (Iterator<LicenseService> it = licenseServiceLoader.iterator(); it.hasNext();) {
			LicenseService licenseService = it.next();
			Properties properties = licenseService.loadLicenseProperties();
			properties.keySet().stream()
					.forEach(k -> union.computeIfAbsent(k, x -> properties.getProperty((String) k)));
		}
		return union;
	}

}
