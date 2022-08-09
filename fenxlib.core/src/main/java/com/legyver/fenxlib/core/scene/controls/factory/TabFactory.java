package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.scene.controls.options.TabOptions;
import com.legyver.fenxlib.core.util.LocationContextOperator;
import javafx.scene.control.Tab;

/**
 * Factory to create a Tab control
 */
public class TabFactory implements StyleableFactory<Tab, TabOptions> {

    @Override
    public Tab makeNode(LocationContext locationContext, TabOptions options) throws CoreException {
        LocationContext tabContext = locationContext.decorateWith(options.getName());
        Tab tab = makeTab();
        tab.setText(options.getText());
        if (options.getContent() != null) {
            LocationContextOperator locationContextOperator = new LocationContextOperator(options.getContent());
            locationContextOperator.reRegister(tabContext, "tab_" + options.getName() + "_content");
            tab.setContent(options.getContent());
        }

        Fenxlib.register(tabContext, tab);
        return tab;
    }

    @Override
    public TabOptions newOptions() {
        return new TabOptions();
    }

    /**
     * Factory method to instantiate a Tab.
     * @return a javafx Tab by default, override if you need something else
     */
    protected Tab makeTab() {
        return new Tab();
    }
}
