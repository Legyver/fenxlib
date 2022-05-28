package com.legyver.fenxlib.config.json.adapter;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.adapter.IConfigAdapter;
import com.legyver.fenxlib.config.json.JsonApplicationConfig;
import com.legyver.fenxlib.config.json.util.JsonConfigInstantiator;

import java.util.Map;

/**
 * Adapter for config files in JSON format
 * @param <T> the type of the application config
 */
public class ConfigFileJsonAdapter<T extends JsonApplicationConfig> implements IConfigAdapter<Map, JsonApplicationConfig> {
    private JsonConfigInstantiator<T> jsonConfigInstantiator;

    @Override
    public JsonApplicationConfig adapt(Map arg) throws CoreException {
        return jsonConfigInstantiator.init(arg);
    }

    /**
     * Set the instantiator to use
     * @param jsonConfigInstantiator the instantiator to use
     */
    public void setJsonConfigInstantiator(JsonConfigInstantiator<T> jsonConfigInstantiator) {
        this.jsonConfigInstantiator = jsonConfigInstantiator;
    }
}
