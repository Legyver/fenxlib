package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXStepperToggleOptions;
import io.github.palexdev.materialfx.controls.MFXStepperToggle;

/**
 * Factory to produce MFXStepperToggle
 */
public class MFXStepperToggleFactory implements NodeFactory<MFXStepperToggle, MFXStepperToggleOptions> {

    @Override
    public MFXStepperToggle makeNode(LocationContext locationContext, MFXStepperToggleOptions options) throws CoreException {
        MFXStepperToggle stepperToggle = new MFXStepperToggle(options.getText(), options.getIcon(), options.getContent());
        Fenxlib.register(locationContext.decorateWith(options.getName()), stepperToggle);
        return stepperToggle;
    }

    @Override
    public MFXStepperToggleOptions newOptions() {
        return new MFXStepperToggleOptions();
    }
}
