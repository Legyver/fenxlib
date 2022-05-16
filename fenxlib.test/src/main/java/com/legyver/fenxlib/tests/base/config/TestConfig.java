package com.legyver.fenxlib.tests.base.config;

import com.legyver.fenxlib.config.json.JsonApplicationConfig;

import java.util.Map;

/**
 * Test config for json-based configs
 */
public class TestConfig extends JsonApplicationConfig {

	/**
	 * Construct a test config that marshals values to-from the specified map
	 * @param map the map to marshal values to
	 */
	public TestConfig(Map map) {
		super(map);
	}

}
