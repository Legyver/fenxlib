package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXFilterPaneOptions;
import io.github.palexdev.materialfx.controls.MFXFilterPane;

/**
 * Factory to produce MFXFilterPane
 */
public class MFXFilterPaneFactory implements NodeFactory<MFXFilterPane, MFXFilterPaneOptions> {

    @Override
    public MFXFilterPane makeNode(LocationContext locationContext, MFXFilterPaneOptions options) throws CoreException {
        MFXFilterPane mfxFilterPane = new MFXFilterPane();
        Fenxlib.register(locationContext, mfxFilterPane);
        return mfxFilterPane;
    }

    @Override
    public MFXFilterPaneOptions newOptions() {
        return new MFXFilterPaneOptions();
    }
}
