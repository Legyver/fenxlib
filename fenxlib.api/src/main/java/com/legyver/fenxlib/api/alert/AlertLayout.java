package com.legyver.fenxlib.api.alert;

import com.legyver.fenxlib.api.layout.IApplicationLayout;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Layout for application alerts.
 * Specifies the content of the alert, and the height and width of the alert.
 */
public class AlertLayout implements IApplicationLayout {
    /**
     * Title of the alert
     */
    private final StringProperty title = new SimpleStringProperty();
    /**
     * Height of the alert
     */
    private final DoubleProperty height = new SimpleDoubleProperty(30);
    /**
     * Width of the alert
     */
    private final DoubleProperty width = new SimpleDoubleProperty(150);
    /**
     * The content wrapped in a StackPane
     */
    private final StackPane pane = new StackPane();

    /**
     * Construct a layout for an alert
     * @param alert the content for the alert
     */
    public AlertLayout(Control alert) {
        setContent(alert);
        setHeightProperty(alert.getPrefHeight() + 10);
        setWidthProperty(alert.getPrefWidth() + 10);
    }

    @Override
    public MenuBar getMenuBar() {
        return null;
    }

    @Override
    public Pane getMainPane() {
        return pane;
    }

    @Override
    public StringProperty titleProperty() {
        return title;
    }

    @Override
    public String getTitle() {
        return title.get();
    }

    @Override
    public double getHeight() {
        return height.get();
    }

    /**
     * Set the height of the layout
     * @param height the height for the popup
     */
    public void setHeightProperty(double height) {
        this.height.set(height);
    }

    @Override
    public double getWidth() {
        return width.get();
    }

    /**
     * Set the width of the layout
     * @param width width for the property
     */
    public void setWidthProperty(double width) {
        this.width.set(width);
    }

    /**
     * Set the content of the layout
     * @param content the content
     */
    public void setContent(Node content) {
        pane.getChildren().clear();
        pane.getChildren().add(content);
    }
}
