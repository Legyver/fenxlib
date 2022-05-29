package com.legyver.fenxlib.core.util.map;

import com.legyver.utils.mapadapt.TypedMapAdapter;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

import java.util.Map;

/**
 * Extension of TypedMapAdapter for JavaFX properties
 */
public class FenxlibTypedMapAdapter extends TypedMapAdapter {

    /**
     * Construct a typed map adapter to support JavaFX properties
     * @param map the map to adapt
     */
    public FenxlibTypedMapAdapter(Map<String, Object> map) {
        super(map);
    }

    /**
     * Get the value as a StringProperty.  No conversion is supported.  Just a simple cast
     * @param name the key of the value
     * @return the value as a string property
     */
    public StringProperty getStringProperty(String name) {
        return get(name, StringProperty.class);
    }

    /**
     * Get the value as a BooleanProperty.  No conversion is supported.  Just a simple cast
     * @param name the key of the value
     * @return the value as a boolean property
     */
    public BooleanProperty getBooleanProperty(String name) {
        return get(name, BooleanProperty.class);
    }
}
