package com.legyver.fenxlib.core.config.load;

import java.io.File;
import java.io.IOException;

public interface ApplicationConfigProvider {

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
