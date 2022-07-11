package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Options for a MFXIconWrapper control
 */
public class MFXIconWrapperOptions extends BaseControlBuilder<MFXIconWrapperOptions> implements StyleableControlOptions<MFXIconWrapper> {
    private String iconDescription;
    private Node iconNode;
    private Double iconSize;
    private Double wrapperSize;
    private Color iconColor;

    /**
     * Specify an icon description.  This is used to lookup the unicode in the FontMap
     * @param iconDescription the icon description
     * @return this builder.
     */
    public MFXIconWrapperOptions iconDescription(String iconDescription) {
        this.iconDescription = iconDescription;
        return me();
    }

    /**
     * Get the icon description.
     * @return the description.
     */
    public String getIconDescription() {
        return iconDescription;
    }

    /**
     * Specify a node that has the icon
     * @param iconNode the node containing the icon
     * @return this builder
     */
    public MFXIconWrapperOptions iconNode(Node iconNode) {
        this.iconNode = iconNode;
        return me();
    }

    /**
     * Get the node containing the icon
     * @return the node
     */
    public Node getIconNode() {
        return iconNode;
    }

    /**
     * Specify the size of the icon
     * @param iconSize the icon size
     * @return this builder
     */
    public MFXIconWrapperOptions iconSize(Double iconSize) {
        this.iconSize = iconSize;
        return me();
    }

    /**
     * Get the icon size
     * @return the icon size
     */
    public Double getIconSize() {
        return iconSize;
    }

    /**
     * Specify the size of the wrapping node
     * @param wrapperSize the size of the wrapping node
     * @return this builder
     */
    public MFXIconWrapperOptions wrapperSize(Double wrapperSize) {
        this.wrapperSize = wrapperSize;
        return me();
    }

    /**
     * Get the size of the wrapping node
     * @return the wrapping node size
     */
    public Double getWrapperSize() {
        return wrapperSize;
    }

    /**
     * Specify the icon color
     * @param color the color
     * @return this builder
     */
    public MFXIconWrapperOptions iconColor(Color color) {
        this.iconColor = iconColor;
        return me();
    }

    /**
     * Get the icon color
     * @return this color
     */
    public Color getIconColor() {
        return iconColor;
    }
}
