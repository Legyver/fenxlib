package com.legyver.fenxlib.core.api.locator;

import javafx.collections.ObservableMap;

/**
 * Get/Set properties for JavaFX components that do not extend Node.
 */
public interface IPropertyAware {
    /**
     * Retrieve the map of properties for the component
     * @return the property map
     */
    ObservableMap<Object, Object> getProperties();
}
