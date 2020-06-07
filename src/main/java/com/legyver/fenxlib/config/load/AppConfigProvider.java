package com.legyver.fenxlib.config.load;

import java.io.File;
import java.io.IOException;

public interface AppConfigProvider {
	/**
	 * Read the appropriate resource.
	 */
	String getApplicationConfigAsString() throws IOException;

	/**
	 * Load config.json
	 * @return
	 */
	File getApplicationConfigFile();
}
