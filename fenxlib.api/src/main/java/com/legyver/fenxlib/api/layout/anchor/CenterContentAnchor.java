package com.legyver.fenxlib.api.layout.anchor;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.layout.PopupDimensions;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Anchor to display a popup over the center of the application
 */
public class CenterContentAnchor extends PopupAnchor {

    @Override
    protected DoubleProperty calculateX(Region positionOver, PopupDimensions popupDimensions) {
        Stage primaryStage = ApplicationContext.getPrimaryStage();

        DoubleProperty widthDiffProperty = new SimpleDoubleProperty();
        widthDiffProperty.bind(primaryStage.widthProperty().subtract(popupDimensions.getPrefWidth()));

        DoubleProperty widthAverageProperty = new SimpleDoubleProperty();
        widthAverageProperty.bind(widthDiffProperty.divide(2.0d));

        DoubleProperty doubleProperty = new SimpleDoubleProperty();
        doubleProperty.bind(primaryStage.xProperty()
                .add(widthAverageProperty)
        );
        return doubleProperty;
    }

    @Override
    protected DoubleProperty calculateY(Region positionOver, PopupDimensions popupDimensions) {
        Stage primaryStage = ApplicationContext.getPrimaryStage();

        DoubleProperty heightDiffProperty = new SimpleDoubleProperty();
        heightDiffProperty.bind(primaryStage.heightProperty().subtract(popupDimensions.getPrefHeight()));

        DoubleProperty heightAverageProperty = new SimpleDoubleProperty();
        heightAverageProperty.bind(heightDiffProperty.divide(2.0d));

        DoubleProperty doubleProperty = new SimpleDoubleProperty();
        doubleProperty.bind(primaryStage.yProperty().add(heightAverageProperty));
        return doubleProperty;
    }
}
