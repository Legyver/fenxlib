package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.layout.VBox;

/**
 * Factory to create a {@link VBox}
 */
public class VBoxFactory implements NodeFactory<VBox> {
    @Override
    public VBox makeNode(LocationContext locationContext) throws CoreException {
        VBox box = makeVBox();
        Fenxlib.register(locationContext, box);
        return box;
    }

    /**
     * Factory method to instantiate a plain JavaFX VBox.
     * @return a JavaFX VBox, override if you want something else
     */
    protected VBox makeVBox() {
        return new VBox();
    }
}
