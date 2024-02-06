package com.legyver.fenxlib.core.layout.options;

import javafx.scene.Node;

/**
 * Region options for the left side of the application
 */
public class LeftRegionOptions extends PreconstructedRegionOptions<LeftRegionOptions> implements ILeftRegionOptions {

    /**
     * Construct a region with the specified content
     * @param content the content to display in this region of the application layout
     */
    public LeftRegionOptions(Node content) {
        super(content);
    }

    /**
     * Construct a region with no content.  It can be specified later with the {@link #setContent(Node)}
     */
    public LeftRegionOptions() {
        this(null);
    }
}
