package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXStepper;

/**
 * Factory to produce a NFXStepper
 */
public class MFXStepperFactory implements NodeFactory<MFXStepper> {
    @Override
    public MFXStepper makeNode(LocationContext locationContext) throws CoreException {
        MFXStepper mfxStepper = new MFXStepper();
        Fenxlib.register(locationContext, mfxStepper);
        return mfxStepper;
    }
}
