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
    @Override
    public ToggleButton makeNode(LocationContext locationContext) throws CoreException {
        ToggleButton toggleButton = makeToggleButton();
        Fenxlib.register(locationContext, toggleButton);
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
