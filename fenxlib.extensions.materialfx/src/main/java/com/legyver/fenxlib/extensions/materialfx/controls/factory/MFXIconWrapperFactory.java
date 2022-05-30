package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Factory to produce a MFXIconWrapper
 */
public class MFXIconWrapperFactory implements NodeFactory<MFXIconWrapper> {
    /**
     * Constructor param for specifying the icon node
     */
    public static final String CONSTRUCTOR_PARAM_ICON_NODE = "iconNode";
    /**
     * Constructor param for specifying the icon size
     */
    public static final String CONSTRUCTOR_PARAM_ICON_SIZE = "iconSize";
    /**
     * Constructor param for specifying the wrapper size
     */
    public static final String CONSTRUCTOR_PARAM_WRAPPER_SIZE = "wrapperSize";
    /**
     * Constructor param for specifying the icon color
     */
    public static final String CONSTRUCTOR_PARAM_ICON_COLOR = "iconColor";
    /**
     * Constructor param for specifying the icon description
     */
    public static final String CONSTRUCTOR_PARAM_ICON_DESCRIPTION = "iconDescription";

    private final String iconDescription;
    private final Node iconNode;
    private final Double iconSize;
    private final Double wrapperSize;
    private final Color iconColor;

    /**
     * Construct a factory for creating an icon wrapper
     * @param iconDescription the description of the icon
     * @param iconNode an instantiated icon
     * @param iconSize the size of the icon
     * @param wrapperSize the size of the wrapper
     * @param iconColor the color of the icon
     */
    public MFXIconWrapperFactory(String iconDescription, Node iconNode, Double iconSize, Double wrapperSize, Color iconColor) {
        this.iconDescription = iconDescription;
        this.iconNode = iconNode;
        this.iconSize = iconSize;
        this.wrapperSize = wrapperSize;
        this.iconColor = iconColor;
    }

    @Override
    public MFXIconWrapper makeNode(LocationContext locationContext) throws CoreException {
        MFXIconWrapper iconWrapper;
        if (iconDescription != null && iconSize != null && iconColor != null && wrapperSize != null) {
            iconWrapper = new MFXIconWrapper(iconDescription, iconSize, iconColor, wrapperSize);
        } else if (iconDescription != null && iconSize != null && wrapperSize != null) {
            iconWrapper = new MFXIconWrapper(iconDescription, iconSize, wrapperSize);
        } else if (iconNode != null && wrapperSize != null) {
            iconWrapper = new MFXIconWrapper(iconNode, wrapperSize);
        } else {
            iconWrapper = new MFXIconWrapper();
        }
        Fenxlib.register(locationContext, iconWrapper);
        return iconWrapper;
    }
}
