package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.fenxlib.core.scene.controls.factory.ProgressBarFactory;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import javafx.scene.control.ProgressBar;

/**
 * Factory to product a MFXProgressBar
 */
public class MFXProgressBarFactory extends ProgressBarFactory {

    @Override
    protected ProgressBar makeProgressBar() {
        return new MFXProgressBar();
    }
}
