package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXIconWrapperOptions;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import javafx.scene.Node;
import javafx.scene.paint.Color;

/**
 * Factory to produce a MFXIconWrapper
 */
public class MFXIconWrapperFactory implements NodeFactory<MFXIconWrapper, MFXIconWrapperOptions> {

    @Override
    public MFXIconWrapper makeNode(LocationContext locationContext, MFXIconWrapperOptions options) throws CoreException {
        MFXIconWrapper iconWrapper;
        String iconDescription = options.getIconDescription();
        Double iconSize = options.getIconSize();
        Double wrapperSize = options.getWrapperSize();
        Color iconColor = options.getIconColor();
        Node iconNode = options.getIconNode();

        if (iconDescription != null && iconSize != null && iconColor != null && wrapperSize != null) {
            iconWrapper = new MFXIconWrapper(iconDescription, iconSize, iconColor, wrapperSize);
        } else if (iconDescription != null && iconSize != null && wrapperSize != null) {
            iconWrapper = new MFXIconWrapper(iconDescription, iconSize, wrapperSize);
        } else if (iconNode != null && wrapperSize != null) {
            iconWrapper = new MFXIconWrapper(iconNode, wrapperSize);
        } else {
            iconWrapper = new MFXIconWrapper();
        }
        Fenxlib.register(locationContext.decorateWith(options.getName()), iconWrapper);
        return iconWrapper;
    }

    @Override
    public MFXIconWrapperOptions newOptions() {
        return new MFXIconWrapperOptions();
    }
}
