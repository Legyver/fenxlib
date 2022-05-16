package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.HBox;

/**
 * Factory to create a {@link HBox}
 */
public class HBoxFactory implements NodeFactory<HBox> {
    @Override
    public HBox makeNode(LocationContext locationContext) throws CoreException {
        HBox hBox = makeHBox();
        Fenxlib.register(locationContext, hBox);
        return hBox;
    }

    /**
     * Factory method to instantiate a plain JavaFX HBox.
     * @return a JavaFX HBox, override if you want something else
     */
    protected HBox makeHBox() {
        return new HBox();
    }
}
