package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ToggleButton;

/**
 * Factory to create a ToggleButton control
 */
public class ToggleButtonFactory implements NodeFactory<ToggleButton> {
    /**
     * Constructor parameter to set as the default text for the toggle button.
     */
    public static final String DEFAULT_TEXT = "defaultText";
    /**
     * Constructor parameter to set if the toggle button is selected by default.
     */
    public static final String SELECTED = "selected";

    private final String defaultText;
    private final Boolean selectedByDefault;

    /**
     * Construct a factory to produce a toggle button
     * @param defaultText the default text for the toggle
     * @param selectedByDefault true if it should be selected by default
     */
    public ToggleButtonFactory(String defaultText, Boolean selectedByDefault) {
        this.defaultText = defaultText;
        this.selectedByDefault = selectedByDefault;
    }

    @Override
    public ToggleButton makeNode(LocationContext locationContext) throws CoreException {
        ToggleButton toggleButton = makeToggleButton();
        Fenxlib.register(locationContext, toggleButton);
        if (defaultText != null) {
            toggleButton.setText(defaultText);
        }
        if (selectedByDefault != null) {
            toggleButton.setSelected(selectedByDefault);
        }
        return toggleButton;
    }

    /**
     * Factory method to instantiate a ToggleButton.
     * @return a javafx ToggleButton by default, override if you need something else
     */
    protected ToggleButton makeToggleButton() {
        return new ToggleButton();
    }
}
