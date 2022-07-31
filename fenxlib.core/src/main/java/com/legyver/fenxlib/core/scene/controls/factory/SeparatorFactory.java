package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.SeparatorOptions;
import javafx.scene.control.Separator;

/**
 * Factory to create a Separator control
 */
public class SeparatorFactory implements NodeFactory<Separator, SeparatorOptions> {
    @Override
    public Separator makeNode(LocationContext locationContext, SeparatorOptions options) throws CoreException {
        Separator separator = makeSeparator();
        Fenxlib.register(locationContext.decorateWith(options.getName()), separator);
        return separator;
    }

    @Override
    public SeparatorOptions newOptions() {
        return new SeparatorOptions();
    }

    /**
     * Factory method to instantiate a Separator.
     * @return a javafx Separator by default, override if you need something else
     */
    protected Separator makeSeparator() {
        return new Separator();
    }
}
