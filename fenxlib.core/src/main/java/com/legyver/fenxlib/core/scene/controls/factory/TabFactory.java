package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.Tab;

/**
 * Factory to create a Tab control
 */
public class TabFactory implements StyleableFactory<Tab> {
    @Override
    public Tab makeNode(LocationContext locationContext) throws CoreException {
        Tab tab = makeTab();
        Fenxlib.register(locationContext, tab);
        return tab;
    }

    /**
     * Factory method to instantiate a Tab.
     * @return a javafx Tab by default, override if you need something else
     */
    protected Tab makeTab() {
        return new Tab();
    }
}
