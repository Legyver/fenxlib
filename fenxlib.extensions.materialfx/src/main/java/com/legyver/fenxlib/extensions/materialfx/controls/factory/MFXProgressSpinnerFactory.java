package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;

/**
 * Factory to create an MFXProgressSpinner
 */
public class MFXProgressSpinnerFactory implements NodeFactory<MFXProgressSpinner> {
    @Override
    public MFXProgressSpinner makeNode(LocationContext locationContext) throws CoreException {
        MFXProgressSpinner spinner = new MFXProgressSpinner();
        Fenxlib.register(locationContext, spinner);
        return spinner;
    }
}
