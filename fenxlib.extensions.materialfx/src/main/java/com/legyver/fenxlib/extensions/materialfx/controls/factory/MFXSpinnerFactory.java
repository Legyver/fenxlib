package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXSpinner;

/**
 * Factory to produce a MFXSpinner
 */
public class MFXSpinnerFactory implements NodeFactory<MFXSpinner> {

    @Override
    public MFXSpinner makeNode(LocationContext locationContext) throws CoreException {
        MFXSpinner spinner = new MFXSpinner();
        Fenxlib.register(locationContext, spinner);
        return spinner;
    }
}
