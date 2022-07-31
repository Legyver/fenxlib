package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXToggleButtonOptions;
import io.github.palexdev.materialfx.controls.MFXToggleButton;

/**
 * Factory to produce a MFXToggleButton
 */
public class MFXToggleButtonFactory implements NodeFactory<MFXToggleButton, MFXToggleButtonOptions> {

    @Override
    public MFXToggleButton makeNode(LocationContext locationContext, MFXToggleButtonOptions options) throws CoreException {
        MFXToggleButton mfxToggleButton;
        if (options.getText() == null) {
            mfxToggleButton = new MFXToggleButton();
        } else {
            mfxToggleButton = new MFXToggleButton(options.getText(), options.getGraphic());
        }
        return mfxToggleButton;
    }

    @Override
    public MFXToggleButtonOptions newOptions() {
        return new MFXToggleButtonOptions();
    }
}
