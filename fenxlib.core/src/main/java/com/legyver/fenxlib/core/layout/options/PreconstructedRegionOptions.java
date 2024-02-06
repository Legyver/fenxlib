package com.legyver.fenxlib.core.layout.options;

import javafx.scene.Node;

/**
 * Options to specify the region content when it is preconstructed
 */
public class PreconstructedRegionOptions<T extends PreconstructedRegionOptions> implements IRegionOptions {
    private Node content;
    private boolean reRegisterComponents;

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

    @Override
    public boolean isReRegisterComponents() {
        return reRegisterComponents;
    }

    /**
     * Builder-type method for specifying if the region content should be re-registered under the region.
     * If specified this will result in the top-most component in a region being re-registered with the name of the region as a parent location context.
     * @param reRegisterComponents flag determining if components should be re-registered
     * @return the extending Options
     */
    public T reRegisterComponents(boolean reRegisterComponents) {
        this.reRegisterComponents = reRegisterComponents;
        return (T) this;
    }
}
