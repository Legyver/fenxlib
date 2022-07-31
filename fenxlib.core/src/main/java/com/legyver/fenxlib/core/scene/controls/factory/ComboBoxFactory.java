package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ComboBoxOptions;
import javafx.scene.control.ComboBox;

/**
 * Factory to create a ComboBox control
 */
public class ComboBoxFactory implements NodeFactory<ComboBox, ComboBoxOptions> {

    @Override
    public ComboBox makeNode(LocationContext locationContext, ComboBoxOptions options) throws CoreException {
        ComboBox comboBox = makeComboBox();
        options.itemsAdapter().setNotNull(list -> comboBox.setItems(list));
        Fenxlib.register(locationContext.decorateWith(options.getName()), comboBox);
        return comboBox;
    }

    @Override
    public ComboBoxOptions newOptions() {
        return new ComboBoxOptions();
    }

    /**
     * Factory method to instantiate a ComboBox.
     * @return a javafx ComboBox by default, override if you need something else
     */
    protected ComboBox makeComboBox() {
        return new ComboBox();
    }
}
