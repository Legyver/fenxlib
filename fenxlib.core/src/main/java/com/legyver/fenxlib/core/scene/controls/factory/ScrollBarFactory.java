package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ScrollBarOptions;
import javafx.scene.control.ScrollBar;

/**
 * Factory to create a ScrollBar control
 */
public class ScrollBarFactory implements NodeFactory<ScrollBar, ScrollBarOptions> {

    @Override
    public ScrollBar makeNode(LocationContext locationContext, ScrollBarOptions options) throws CoreException {
        ScrollBar scrollBar = makeScrollBar();
        Fenxlib.register(locationContext.decorateWith(options.getName()), scrollBar);
        return scrollBar;
    }

    @Override
    public ScrollBarOptions newOptions() {
        return new ScrollBarOptions();
    }

    /**
     * Factory method to instantiate a ScrollBar.
     * @return a javafx ScrollBar by default, override if you need something else
     */
    protected ScrollBar makeScrollBar() {
        return new ScrollBar();
    }
}
