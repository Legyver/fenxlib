package com.legyver.fenxlib.core.layout.options;

import javafx.scene.Node;

/**
 * Options describing the center of the application
 */
public class CenterRegionOptions extends PreconstructedRegionOptions<CenterRegionOptions> implements ICenterRegionOptions {
    /**
     * Construct a region with the specified content
     * @param content the content to display in this region of the application layout
     */
    public CenterRegionOptions(Node content) {
        super(content);
    }

    /**
     * Construct a region with no content.  It can be specified later with the {@link #setContent(Node)}
     */
    public CenterRegionOptions() {
        this(null);
    }
}
