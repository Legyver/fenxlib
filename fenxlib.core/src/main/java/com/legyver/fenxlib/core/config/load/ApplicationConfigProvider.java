package com.legyver.fenxlib.core.config.load;

/**
 * Hook to provide an application config
 */
public interface ApplicationConfigProvider {

	/**
	 * return the filename for the application config
	 * @return the application config file
	 */
	String getApplicationConfigFilename();
}
