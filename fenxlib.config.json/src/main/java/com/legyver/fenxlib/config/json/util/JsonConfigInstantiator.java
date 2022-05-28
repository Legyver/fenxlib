package com.legyver.fenxlib.config.json.util;

import com.legyver.fenxlib.api.config.ApplicationConfigInstantiator;
import com.legyver.fenxlib.config.json.JsonApplicationConfig;

/**
 * Instantiator to instantiate JSON application configs
 * @param <T> the type of the config file
 */
public interface JsonConfigInstantiator<T extends JsonApplicationConfig> extends ApplicationConfigInstantiator<JsonApplicationConfig>  {
}
