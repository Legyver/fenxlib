package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.fenxlib.core.scene.controls.factory.ScrollPaneFactory;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.scene.control.ScrollPane;

/**
 * Factory to produce an MFXScrollPane
 */
public class MFXScrollPaneFactory extends ScrollPaneFactory {
    @Override
    protected ScrollPane makeScrollPane() {
        return new MFXScrollPane();
    }
}
