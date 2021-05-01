package com.legyver.fenxlib.core.impl.util;

import com.legyver.fenxlib.core.impl.config.load.ApplicationConfigProvider;

public class TestApplicationResource implements ApplicationConfigProvider {
	private final String filename;

	public TestApplicationResource(String filename) {
		this.filename = filename;
	}

	@Override
	public String getApplicationConfigFilename() {
		return filename;
	}
}