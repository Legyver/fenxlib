package com.legyver.fenxlib.api.layout.anchor;

import com.legyver.fenxlib.api.layout.PopupDimensions;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * The anchor for a popup to determine position within an application screen
 */
public abstract class PopupAnchor implements AlertAnchor {
    /**
     * Set the x,y for the Popup with respect to the application screen
     * @param popupStage the popup stage
     * @param positionOver optional region to display the popup over
     * @param popupDimensions dimensions for the popup used to calculate offsets
     */
    public final void position(Stage popupStage, Region positionOver, PopupDimensions popupDimensions) {
        DoubleProperty x = calculateX(positionOver, popupDimensions);
        popupStage.setX(x.getValue());
        x.addListener(new ChangeListener<>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                popupStage.setX(newValue.doubleValue());
            }
        });
        DoubleProperty y = calculateY(positionOver, popupDimensions);
        popupStage.setY(sumY(y.getValue(), popupDimensions.getOffsetY()));
        y.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                popupStage.setY(sumY(newValue.doubleValue() , popupDimensions.getOffsetY()));
            }
        });
        popupDimensions.offsetYProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                popupStage.setY(sumY(y.doubleValue() , popupDimensions.getOffsetY()));
            }
        });
    }

    /**
     * Calculate the x value with respect to the application or the region to position over
     * @param positionOver the region to position over
     * @param popupDimensions the dimensions of the popup which may include offset
     * @return the x value for the stage
     */
    protected abstract DoubleProperty calculateX(Region positionOver, PopupDimensions popupDimensions);
    /**
     * Calculate the y value with respect to the application or the region to position over
     * @param positionOver the region to position over
     * @param popupDimensions the dimensions of the popup which may include offset
     * @return the y value for the stage
     */
    protected abstract DoubleProperty calculateY(Region positionOver, PopupDimensions popupDimensions);

    /**
     * Sum or difference two doubles depending on the direction of offset
     * @param boundValue the value bound
     * @param offsetY the offset in the y position
     * @return the sum or difference
     */
    protected abstract double sumY(Double boundValue, Double offsetY);
}
