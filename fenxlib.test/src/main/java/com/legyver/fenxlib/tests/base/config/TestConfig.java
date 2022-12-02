package com.legyver.fenxlib.tests.base.config;

import com.legyver.fenxlib.api.config.ApplicationConfig;
import com.legyver.fenxlib.core.config.CoreConfigSection;
import com.legyver.fenxlib.core.config.ICoreApplicationConfig;

/**
 * Test config for json-based configs
 */
public class TestConfig extends ApplicationConfig implements ICoreApplicationConfig {

	private CoreConfigSection coreConfig = new CoreConfigSection();

	@Override
	public CoreConfigSection getCoreConfig() {
		return coreConfig;
	}

	@Override
	public void setCoreConfig(CoreConfigSection coreConfig) {
		this.coreConfig = new CoreConfigSection();
	}

}
