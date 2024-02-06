package com.legyver.fenxlib.core.layout.options;

import javafx.scene.Node;

/**
 * Options for the top of the application in a border-pane or split-pane layout.
 * The menu bar is not part of the layout as that is specified separately in the {@link com.legyver.fenxlib.core.layout.BaseApplicationLayout}
 * and is rendered specific to the operating system via standard mechanism.
 */
public class TopRegionOptions extends PreconstructedRegionOptions<TopRegionOptions> implements ITopRegionOptions {

    /**
     * Construct a top region with the specified content
     * @param content the content to display at the top of the application layout
     */
    public TopRegionOptions(Node content) {
        super(content);
    }

    /**
     * Construct a top region with no content
     */
    public TopRegionOptions() {
        this(null);
    }

}
