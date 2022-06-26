package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.scene.controls.options.ScrollBarOptions;
import com.legyver.fenxlib.core.scene.controls.options.ScrollPaneOptions;
import javafx.scene.control.ScrollPane;

/**
 * Factory to create a ScrollPane control
 */
public class ScrollPaneFactory implements NodeFactory<ScrollPane, ScrollPaneOptions> {

    @Override
    public ScrollPane makeNode(LocationContext locationContext, ScrollPaneOptions options) throws CoreException {
        ScrollPane scrollPane = makeScrollPane();
        Fenxlib.register(locationContext.decorateWith(options.getName()), scrollPane);
        return scrollPane;
    }

    @Override
    public ScrollPaneOptions newOptions() {
        return new ScrollPaneOptions();
    }

    /**
     * Factory method to instantiate a ScrollPane.
     * @return a javafx ScrollPane by default, override if you need something else
     */
    protected ScrollPane makeScrollPane() {
        return new ScrollPane();
    }
}
