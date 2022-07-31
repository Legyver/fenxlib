package com.legyver.fenxlib.widgets.snackbar.alert;

import com.legyver.fenxlib.api.alert.IAlert;
import com.legyver.fenxlib.api.alert.ICloseableAlert;
import com.legyver.fenxlib.api.layout.PopupDimensions;
import com.legyver.fenxlib.api.layout.anchor.PopupAnchor;
import com.legyver.fenxlib.core.util.DelayedAction;
import javafx.application.Platform;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Virtual flow of alerts for grouping alerts in the same region.
 */
public class VirtualAlertFlow extends Control {
    private final AlertFlowDirection alertFlowDirection;
    private final PopupAnchor popupAnchor;
    private List<IAlert> alertList = Collections.synchronizedList(new ArrayList<>());

    /**
     * Construct a virtual flow for grouping alerts in a region
     * @param alertFlowDirection the direction to append new alerts when adding to current alert stack
     * @param popupAnchor the anchor determining the region for the alerts
     */
    public VirtualAlertFlow(AlertFlowDirection alertFlowDirection, PopupAnchor popupAnchor) {
        this.alertFlowDirection = alertFlowDirection;
        this.popupAnchor = popupAnchor;
    }

    /**
     * Add an alert to a flow.
     * If there are already alerts in the flow, add the new alert in the direction of the flow.
     * If the alert stage is dismissed, the flow is adjusted to fill in any gaps.
     * If the alert stage has a timeout, the alert will be closed after that timeout, and the flow adjusted to fill any gaps.
     *
     * @param alert the alert to display
     * @param stage the stage to display the alert on
     */
    public void addAlert(IAlert alert, Stage stage) {
        int index = alertList.size();
        alertList.add(alert);

        Control control = (Control) alert;
        PopupDimensions popupDimensions = new PopupDimensions.Builder()
                .maxHeight(control.getMaxHeight())
                .prefHeight(control.getPrefHeight())
                .minHeight(control.getPrefHeight())
                .maxWidth(control.getMaxWidth())
                .prefWidth(control.getPrefWidth())
                .minWidth(control.getMinWidth())
                .offsetY(index * (control.getPrefHeight() + 10)).build();

        popupAnchor.position(stage, null, popupDimensions);

        Runnable removeAlert = () -> {
            alertList.remove(alert);
            if (alertFlowDirection == AlertFlowDirection.BOTTOM_UP) {
                for (int i = index; i < alertList.size(); i++) {
                    //adjust vertical positioning of all effected popups
                    popupDimensions
                            .setOffsetY(index * (control.getPrefHeight() + 10));
                }
            }
        };
        //when a popup stage is dismissed, adjust all effected popups
        stage.setOnCloseRequest(event -> Platform.runLater(removeAlert));

        if (alert.getTimeoutInMillis() > -1) {
            Runnable closeAndRemoveAlert = () -> {
                stage.close();
                removeAlert.run();
            };

            //auto-closed popups
            Platform.runLater(new DelayedAction(closeAndRemoveAlert, alert.getTimeoutInMillis()));
        }

        if (alert instanceof ICloseableAlert) {
            ((ICloseableAlert) alert).closeProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    //user clicked on 'x'
                    stage.close();
                }
            });
        }
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new VirtualAlertFlowSkin(this);
    }


    /**
     * Flow of alerts when more than one alert is in the same region
     */
    public enum AlertFlowDirection {
        /**
         * Display subsequent alerts beneath earlier alerts
         */
        TOP_DOWN,
        /**
         * Display subsequent alerts above earlier alerts
         */
        BOTTOM_UP
    }

}
