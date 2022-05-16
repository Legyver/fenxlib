package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ScrollPane;

/**
 * Factory to create a ScrollPane control
 */
public class ScrollPaneFactory implements NodeFactory<ScrollPane> {
    @Override
    public ScrollPane makeNode(LocationContext locationContext) throws CoreException {
        ScrollPane scrollPane = makeScrollPane();
        Fenxlib.register(locationContext, scrollPane);
        return scrollPane;
    }

    /**
     * Factory method to instantiate a ScrollPane.
     * @return a javafx ScrollPane by default, override if you need something else
     */
    protected ScrollPane makeScrollPane() {
        return new ScrollPane();
    }
}
