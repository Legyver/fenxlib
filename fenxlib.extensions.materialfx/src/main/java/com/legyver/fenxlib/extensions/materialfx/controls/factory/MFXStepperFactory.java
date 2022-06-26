package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXStepperOptions;
import io.github.palexdev.materialfx.controls.MFXStepper;

/**
 * Factory to produce a NFXStepper
 */
public class MFXStepperFactory implements NodeFactory<MFXStepper, MFXStepperOptions> {
    @Override
    public MFXStepper makeNode(LocationContext locationContext, MFXStepperOptions options) throws CoreException {
        MFXStepper mfxStepper = new MFXStepper();
        Fenxlib.register(locationContext.decorateWith(options.getName()), mfxStepper);
        return mfxStepper;
    }

    @Override
    public MFXStepperOptions newOptions() {
        return new MFXStepperOptions();
    }

}
