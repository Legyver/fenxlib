package com.legyver.fenxlib.core.layout.options;

import javafx.scene.Node;

/**
 * Options to specify the region content when it is preconstructed
 */
public class PreconstructedRegionOptions implements IRegionOptions {
    private Node content;

    /**
     * Construct a region with the specified content
     * @param content the content to display in this region of the application layout
     */
    public PreconstructedRegionOptions(Node content) {
        this.content = content;
    }

    /**
     * Construct a region with no content.  It can be specified later with the {@link #setContent(Node)}
     */
    public PreconstructedRegionOptions() {
        this(null);
    }

    /**
     * Get the content to position in this region of the application layout
     * @return the content
     */
    public Node getContent() {
        return content;
    }

    /**
     * Set the content to position in the application layout
     * @param content the content to use for this region of the application
     */
    public void setContent(Node content) {
        this.content = content;
    }
}
