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
}
