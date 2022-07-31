package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.CheckMenuItemOptions;
import javafx.scene.control.CheckMenuItem;

/**
 * Factory to create a CheckMenuItem control
 */
public class CheckMenuItemFactory implements StyleableFactory<CheckMenuItem, CheckMenuItemOptions> {

    @Override
    public CheckMenuItem makeNode(LocationContext locationContext, CheckMenuItemOptions options) throws CoreException {
        CheckMenuItem checkMenuItem = makeCheckMenuItem();
        checkMenuItem.setText(options.getText());
        options.selectedAdapter().setNotNull(flag -> checkMenuItem.setSelected(flag));
        options.disabledAdapter().setNotNull(flag -> checkMenuItem.setDisable(flag));
        Fenxlib.register(locationContext.decorateWith(options.getName()), checkMenuItem);
        return checkMenuItem;
    }

    @Override
    public CheckMenuItemOptions newOptions() {
        return new CheckMenuItemOptions();
    }

    /**
     * Factory method to instantiate a CheckMenuItem.
     * @return a javafx CheckMenuItem by default, override if you need something else
     */
    protected CheckMenuItem makeCheckMenuItem() {
        return new CheckMenuItem();
    }
}
