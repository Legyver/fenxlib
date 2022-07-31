package com.legyver.fenxlib.api.layout;

import javafx.beans.property.StringProperty;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

/**
 * Interface providing access to the basic layout of an application
 */
public interface IApplicationLayout {
    /**
     * Get the menu bar set on the layout
     * @return the menu bar
     */
    MenuBar getMenuBar();
    /**
     * Get the main application pane
     * @return the main application pane
     */
    Region getMainPane();

    /**
     * The property the application title in the primary stage will be bound to
     * @return the title property
     */
    StringProperty titleProperty();

    /**
     * Get the title for the application
     * @return the application title
     */
    String getTitle();

    /**
     * Get the height of the application for the primary stage
     * @return the default height
     */
    double getHeight();

    /**
     * Get the width of the application for the primary stage
     * @return the default width
     */
    double getWidth();
}
