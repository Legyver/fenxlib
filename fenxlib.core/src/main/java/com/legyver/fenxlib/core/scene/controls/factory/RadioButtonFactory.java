package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.scene.controls.options.RadioButtonOptions;
import javafx.scene.control.RadioButton;

/**
 * Factory to create a RadioButton control
 */
public class RadioButtonFactory implements NodeFactory<RadioButton, RadioButtonOptions> {

    @Override
    public RadioButton makeNode(LocationContext locationContext, RadioButtonOptions options) throws CoreException {
        RadioButton radioButton = makeRadioButton();
        Fenxlib.register(locationContext.decorateWith(options.getName()), radioButton);
        return radioButton;
    }

    @Override
    public RadioButtonOptions newOptions() {
        return new RadioButtonOptions();
    }

    /**
     * Factory method to instantiate a RadioButton.
     * @return a javafx RadioButton by default, override if you need something else
     */
    protected RadioButton makeRadioButton() {
        return new RadioButton();
    }
}
