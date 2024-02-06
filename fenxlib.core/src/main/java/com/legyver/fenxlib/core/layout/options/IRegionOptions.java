package com.legyver.fenxlib.core.layout.options;

import javafx.scene.Node;

/**
 * Tagging interface for various types of region options used when specifying application region content
 */
public interface IRegionOptions {
    /**
     * Get the content to be included in a region of the application
     * @return the content
     */
    Node getContent();

    /**
     * Check if region components should be re-registered under the region name.
     * By default, this is false
     * @return true or false
     */
    default boolean isReRegisterComponents() {
        return false;
    }
}
