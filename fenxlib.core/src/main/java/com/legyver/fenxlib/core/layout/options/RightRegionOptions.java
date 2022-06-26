package com.legyver.fenxlib.core.layout.options;

import javafx.scene.Node;

/**
 * Region options for the right of the application
 */
public class RightRegionOptions extends PreconstructedRegionOptions implements IRightRegionOptions {
    /**
     * Construct a region with the specified content
     * @param content the content to display in this region of the application layout
     */
    public RightRegionOptions(Node content) {
        super(content);
    }

    /**
     * Construct a region with no content.  It can be specified later with the {@link #setContent(Node)}
     */
    public RightRegionOptions() {
        this(null);
    }
}
