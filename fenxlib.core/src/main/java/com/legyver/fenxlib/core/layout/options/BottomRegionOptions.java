package com.legyver.fenxlib.core.layout.options;

import javafx.scene.Node;

/**
 * Default BottomRegionOptions
 */
public class BottomRegionOptions extends PreconstructedRegionOptions implements IBottomRegionOptions {
    /**
     * Construct a region with the specified content
     * @param content the content to display in this region of the application layout
     */
    public BottomRegionOptions(Node content) {
        super(content);
    }

    /**
     * Construct a region with no content.  It can be specified later with the {@link #setContent(Node)}
     */
    public BottomRegionOptions() {
        this(null);
    }
}
