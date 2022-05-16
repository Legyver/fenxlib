package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.Separator;

/**
 * Factory to create a Separator control
 */
public class SeparatorFactory implements NodeFactory<Separator> {
    @Override
    public Separator makeNode(LocationContext locationContext) throws CoreException {
        Separator separator = makeSeparator();
        Fenxlib.register(locationContext, separator);
        return separator;
    }

    /**
     * Factory method to instantiate a Separator.
     * @return a javafx Separator by default, override if you need something else
     */
    protected Separator makeSeparator() {
        return new Separator();
    }
}
