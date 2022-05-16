package com.legyver.fenxlib.core.controls.popup;

import com.legyver.fenxlib.api.alert.IAlert;
import com.legyver.fenxlib.api.alert.IAlertPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

/**
 * Alert pane that will display application alerts over either the top-right, top-left, bottom-right or bottom-left of an application
 */
public class AlertPane extends Control implements IAlertPane {

    private final ObservableList<IAlert> topLeftAlerts = FXCollections.observableArrayList();
    private final ObservableList<IAlert> topRightAlerts = FXCollections.observableArrayList();
    private final ObservableList<IAlert> bottomLeftAlerts = FXCollections.observableArrayList();
    private final ObservableList<IAlert> bottomRightAlerts = FXCollections.observableArrayList();

    @Override
    protected Skin<?> createDefaultSkin() {
        return new AlertPaneSkin(this);
    }

    /**
     * Get alerts to be displayed in the top left of the main application
     * @return top left alerts
     */
    public ObservableList<IAlert> getTopLeftAlerts() {
        return topLeftAlerts;
    }

    /**
     * Get alerts to be displayed in the top right of the main application
     * @return top right alerts
     */
    public ObservableList<IAlert> getTopRightAlerts() {
        return topRightAlerts;
    }

    /**
     * Get alerts to be displayed in the bottom left of the main application
     * @return bottom left alerts
     */
    public ObservableList<IAlert> getBottomLeftAlerts() {
        return bottomLeftAlerts;
    }

    /**
     * Get alerts to be displayed in the bottom right of the main application
     * @return bottom right alerts
     */
    public ObservableList<IAlert> getBottomRightAlerts() {
        return bottomRightAlerts;
    }
}
