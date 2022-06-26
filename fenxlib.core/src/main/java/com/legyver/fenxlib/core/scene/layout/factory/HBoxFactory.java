package com.legyver.fenxlib.core.scene.layout.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.scene.layout.options.HBoxOptions;
import javafx.scene.layout.HBox;

/**
 * Factory to create a {@link HBox}
 */
public class HBoxFactory implements NodeFactory<HBox, HBoxOptions> {

    @Override
    public HBox makeNode(LocationContext locationContext, HBoxOptions options) throws CoreException {
        HBox hBox = makeHBox();
        Fenxlib.register(locationContext.decorateWith(options.getName()), hBox);
        return hBox;
    }

    @Override
    public HBoxOptions newOptions() {
        return new HBoxOptions();
    }

    /**
     * Factory method to instantiate a plain JavaFX HBox.
     * @return a JavaFX HBox, override if you want something else
     */
    protected HBox makeHBox() {
        return new HBox();
    }
}
