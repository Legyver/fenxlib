package com.legyver.fenxlib.tests.base.config;

import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.api.config.parts.LastOpened;
import com.legyver.fenxlib.api.config.parts.RecentFiles;
import com.legyver.fenxlib.core.config.CoreConfigSection;
import com.legyver.fenxlib.core.config.ICoreApplicationConfig;

/**
 * Test config for json-based configs
 */
public class TestConfig extends ApplicationConfig implements ICoreApplicationConfig {

	private RecentFiles recentFiles = new RecentFiles();
	private LastOpened lastOpened = new LastOpened();

	private CoreConfigSection configSection = new CoreConfigSection();

	/**
	 * Test configuration that loads in-memory versus from file
	 */
	public TestConfig() {
		configSection.setLastOpened(lastOpened);
		configSection.setRecentFiles(recentFiles);
	}

	@Override
	public CoreConfigSection getCoreConfig() {
		return configSection;
	}

}
