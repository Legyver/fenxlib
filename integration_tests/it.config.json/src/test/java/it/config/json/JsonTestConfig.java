package it.config.json;

import com.legyver.fenxlib.config.json.JsonApplicationConfig;

import java.util.Map;

public class JsonTestConfig extends JsonApplicationConfig {
    /**
     * Construct an Application Config the will marshall the config to-from JSON
     *
     * @param map the map of values to save
     */
    public JsonTestConfig(Map map) {
        super(map);
    }
}
