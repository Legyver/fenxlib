package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXMagnifierPaneOptions;
import io.github.palexdev.materialfx.controls.MFXMagnifierPane;

/**
 * Factory to produce a MFXMagnifierPane
 */
public class MFXMagnifierPaneFactory implements NodeFactory<MFXMagnifierPane, MFXMagnifierPaneOptions> {

    @Override
    public MFXMagnifierPane makeNode(LocationContext locationContext, MFXMagnifierPaneOptions options) throws CoreException {
        MFXMagnifierPane magnifierPane = new MFXMagnifierPane(options.getContent());
        Fenxlib.register(locationContext, magnifierPane);
        return magnifierPane;
    }

    @Override
    public MFXMagnifierPaneOptions newOptions() {
        return new MFXMagnifierPaneOptions();
    }
}
