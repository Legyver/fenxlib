package com.legyver.fenxlib.api.files.util;

import java.util.Map;

/**
 * Instantiate a config POJO
 * @param <T> the type of the instantiated config POJO
 */
public interface ConfigInstantiator<T> {
    /**
     * Initialize a POJO from a Map of values
     * @param map the values to set on the POJO
     * @return the POJO
     */
    T init(Map map);
}
