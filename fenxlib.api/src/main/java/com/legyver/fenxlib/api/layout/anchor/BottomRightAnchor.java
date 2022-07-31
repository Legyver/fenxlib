package com.legyver.fenxlib.api.layout.anchor;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.layout.PopupDimensions;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Anchor to display content over the bottom right of the application
 */
public class BottomRightAnchor extends PopupAnchor {
    private final double insetX;
    private final double insetY;

    /**
     * Construct an anchor to display alerts in the bottom right of the application screen
     * @param insetX the amount to inset relative to the x-axis
     * @param insetY the amount to inset relative to the y-axis
     */
    public BottomRightAnchor(double insetX, double insetY) {
        this.insetX = insetX;
        this.insetY = insetY;
    }

    /**
     * Construct an anchor to display alerts in the bottom right of the application screen.
     * Applies default insets of (30, 30)
     */
    public BottomRightAnchor() {
        this(30, 30);
    }

    @Override
    protected DoubleProperty calculateX(Region positionOver, PopupDimensions popupDimensions) {
        Stage primaryStage = ApplicationContext.getPrimaryStage();
        DoubleProperty doubleProperty = new SimpleDoubleProperty();
        doubleProperty.bind(primaryStage.xProperty()
                .add(primaryStage.widthProperty())
                .subtract(popupDimensions.getPrefWidth())
                .subtract(insetX));
        return doubleProperty;
    }

    @Override
    protected DoubleProperty calculateY(Region positionOver, PopupDimensions popupDimensions) {
        Stage primaryStage = ApplicationContext.getPrimaryStage();
        DoubleProperty doubleProperty = new SimpleDoubleProperty();
        doubleProperty.bind(primaryStage.yProperty()
                .add(primaryStage.heightProperty())
                .subtract(popupDimensions.getPrefHeight())
                .subtract(insetY));
        return doubleProperty;
    }

    @Override
    protected double sumY(Double boundValue, Double offsetY) {
        return boundValue - offsetY;
    }
}
