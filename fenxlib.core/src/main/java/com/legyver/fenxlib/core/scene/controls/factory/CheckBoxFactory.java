package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.scene.controls.options.CheckBoxOptions;
import javafx.scene.control.CheckBox;

/**
 * Factory to create a CheckBox control
 */
public class CheckBoxFactory implements NodeFactory<CheckBox, CheckBoxOptions> {

    @Override
    public CheckBox makeNode(LocationContext locationContext, CheckBoxOptions options) throws CoreException {
        CheckBox checkBox = makeCheckBox();
        checkBox.setText(options.getText());
        new BooleanAdapter(options.isSelected()).setNotNull(flag -> checkBox.setSelected(flag));
        Fenxlib.register(locationContext.decorateWith(options.getName()), checkBox);
        return checkBox;
    }

    @Override
    public CheckBoxOptions newOptions() {
        return new CheckBoxOptions();
    }

    /**
     * Factory method to instantiate a CheckBox.
     * @return a javafx CheckBox by default, override if you need something else
     */
    protected CheckBox makeCheckBox() {
        return new CheckBox();
    }
}
