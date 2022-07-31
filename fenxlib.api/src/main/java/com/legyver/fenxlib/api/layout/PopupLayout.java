package com.legyver.fenxlib.api.layout;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.scene.controls.options.ScrollPaneOptions;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Layout for popups.
 * Wraps the content to display in a scroll pane
 */
public class PopupLayout implements IApplicationLayout {
    private static final Logger logger = LogManager.getLogger(PopupLayout.class);
    private static final double insetSize = 20d;

    private final ScrollPane scrollPane;
    private final StringProperty popupTitle = new SimpleStringProperty();
    private final PopupDimensions popupDimensions;

    /**
     * Construct a PopupLayout to present the content
     * @param content the content to display in the popup
     * @param popupName the name of the popup content for querying
     * @param popupDimensions dimensions for the popup
     */
    public PopupLayout(Node content, String popupName, PopupDimensions popupDimensions) {
        ScrollPane pane = null;
        try {
            pane = ControlsFactory.make(ScrollPane.class, new ScrollPaneOptions()
                    .name(popupName)
                    .content(content));
            pane.setPadding(new Insets(insetSize));
            pane.setFitToWidth(true);
            pane.setFitToHeight(true);
        } catch (CoreException exception) {
            logger.error(exception);
        }
        this.scrollPane = pane;
        this.popupTitle.set(popupName);
        this.popupDimensions = popupDimensions;
    }

    @Override
    public MenuBar getMenuBar() {
        return null;
    }

    @Override
    public Region getMainPane() {
        return scrollPane;
    }

    @Override
    public StringProperty titleProperty() {
        return popupTitle;
    }

    @Override
    public String getTitle() {
        return popupTitle.get();
    }

    @Override
    public double getHeight() {
        return popupDimensions.getPrefHeight() + insetSize * 2;
    }

    @Override
    public double getWidth() {
        return popupDimensions.getPrefWidth() + insetSize * 2;
    }
}
