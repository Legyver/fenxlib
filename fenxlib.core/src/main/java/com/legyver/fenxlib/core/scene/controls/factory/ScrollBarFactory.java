package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ScrollBar;

/**
 * Factory to create a ScrollBar control
 */
public class ScrollBarFactory implements NodeFactory<ScrollBar> {
    @Override
    public ScrollBar makeNode(LocationContext locationContext) throws CoreException {
        ScrollBar scrollBar = makeScrollBar();
        Fenxlib.register(locationContext, scrollBar);
        return scrollBar;
    }

    /**
     * Factory method to instantiate a ScrollBar.
     * @return a javafx ScrollBar by default, override if you need something else
     */
    protected ScrollBar makeScrollBar() {
        return new ScrollBar();
    }
}
