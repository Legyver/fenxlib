package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.scene.controls.options.ToggleButtonOptions;
import javafx.scene.control.ToggleButton;

/**
 * Factory to create a ToggleButton control
 */
public class ToggleButtonFactory implements NodeFactory<ToggleButton, ToggleButtonOptions> {

    @Override
    public ToggleButton makeNode(LocationContext locationContext, ToggleButtonOptions options) throws CoreException {
        ToggleButton toggleButton = makeToggleButton();
        Fenxlib.register(locationContext, toggleButton);
        if (options.getText() != null) {
            toggleButton.setText(options.getText());
        }
        options.selectedAdapter().setNotNull(selectedByDefault -> toggleButton.setSelected(selectedByDefault));
        return toggleButton;
    }

    @Override
    public ToggleButtonOptions newOptions() {
        return new ToggleButtonOptions();
    }

    /**
     * Factory method to instantiate a ToggleButton.
     * @return a javafx ToggleButton by default, override if you need something else
     */
    protected ToggleButton makeToggleButton() {
        return new ToggleButton();
    }
}
