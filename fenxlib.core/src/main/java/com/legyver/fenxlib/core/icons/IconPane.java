package com.legyver.fenxlib.core.icons;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * Adapter to house various icon implementations
 **/
public class IconPane extends StackPane {
    /**
     * Construct an IconPane to wrap whatever Icon implementation we're dealing with
     * @param pane the pane wrapping the icon
     */
    public IconPane(Pane pane) {
        super(pane);
    }
}
