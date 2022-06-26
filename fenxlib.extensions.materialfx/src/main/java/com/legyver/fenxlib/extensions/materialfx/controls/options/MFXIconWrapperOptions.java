package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXIconWrapper;
import javafx.scene.Node;
import javafx.scene.paint.Color;

public class MFXIconWrapperOptions extends BaseControlBuilder<MFXIconWrapperOptions> implements StyleableControlOptions<MFXIconWrapper> {
    private String iconDescription;
    private Node iconNode;
    private Double iconSize;
    private Double wrapperSize;
    private Color iconColor;

    public MFXIconWrapperOptions iconDescription(String iconDescription) {
        this.iconDescription = iconDescription;
        return me();
    }

    public String getIconDescription() {
        return iconDescription;
    }

    public MFXIconWrapperOptions iconNode(Node iconNode) {
        this.iconNode = iconNode;
        return me();
    }

    public Node getIconNode() {
        return iconNode;
    }

    public MFXIconWrapperOptions iconSize(Double iconSize) {
        this.iconSize = iconSize;
        return me();
    }

    public Double getIconSize() {
        return iconSize;
    }

    public MFXIconWrapperOptions wrapperSize(Double wrapperSize) {
        this.wrapperSize = wrapperSize;
        return me();
    }

    public Double getWrapperSize() {
        return wrapperSize;
    }

    public MFXIconWrapperOptions iconColor(Color color) {
        this.iconColor = iconColor;
        return me();
    }

    public Color getIconColor() {
        return iconColor;
    }
}
