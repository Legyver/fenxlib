package com.legyver.fenxlib.api.config.adapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Map to introduce a bit more robustness than available with standard maps for type conversion
 */
public class ConfigAdapterMap {
    private final Map<String, IConfigAdapter> adapters = new HashMap<>();

    /**
     * Get the adapter for the name
     * @param partName the name of the part of the config the adapter is supposed to adapt
     * @return the adapter
     */
    public IConfigAdapter get(String partName) {
        return adapters.get(partName);
    }

    /**
     * Get the adapter for the config
     * @param configAdapter the config part type
     * @return config adapter
     */
    public IConfigAdapter get(ConfigAdapterPartType configAdapter) {
        return get(configAdapter.name());
    }

    /**
     * Register the adapter for the specified part of the config
     * @param partName the name of the part of the config file the adapter adapts
     * @param adapter the adapter that does the adapting
     */
    public void put(String partName, IConfigAdapter adapter) {
        this.adapters.put(partName, adapter);
    }

    /**
     * Register the adapter for the specified part of the config
     * @param configAdapterType the part of the config it adapts
     * @param adapter the adapter that does the adapting
     */
    public void put(ConfigAdapterPartType configAdapterType, IConfigAdapter adapter) {
        put(configAdapterType.name(), adapter);
    }

}
