package com.legyver.fenxlib.tests.base.config;

import com.legyver.fenxlib.api.config.load.ApplicationConfigProvider;

/**
 * Ability to add a custom application config file
 */
public class TestApplicationResource implements ApplicationConfigProvider {
	private final String filename;

	/**
	 * Constructor to specify the application resource to use
	 * @param filename the name of the file
	 */
	public TestApplicationResource(String filename) {
		this.filename = filename;
	}

	@Override
	public String getApplicationConfigFilename() {
		return filename;
	}
}