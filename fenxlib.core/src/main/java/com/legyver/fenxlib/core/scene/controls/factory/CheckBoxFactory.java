package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.CheckBox;

/**
 * Factory to create a CheckBox control
 */
public class CheckBoxFactory implements NodeFactory<CheckBox> {

    /**
     * Constructor parameter to specify text for the checkbox
     */
    public static final String CONSTRUCTOR_PARAM_TEXT = "text";
    /**
     * Constructor parameter to specify if the checkbox is selected
     */
    public static final String CONSTRUCTOR_PARAM_IS_SELECTED = "selected";

    private final String text;
    private final BooleanAdapter selected;

    /**
     * Construct a Button.
     * @param text the text for the checkbox
     * @param selected true if the checkbox is selected
     */
    public CheckBoxFactory(String text, Boolean selected) {
        this.text = text;
        this.selected = new BooleanAdapter(selected);
    }

    @Override
    public CheckBox makeNode(LocationContext locationContext) throws CoreException {
        CheckBox checkBox = makeCheckBox();
        checkBox.setText(text);
        selected.setNotNull(flag -> checkBox.setSelected(flag));
        Fenxlib.register(locationContext, checkBox);
        return checkBox;
    }

    /**
     * Factory method to instantiate a CheckBox.
     * @return a javafx CheckBox by default, override if you need something else
     */
    protected CheckBox makeCheckBox() {
        return new CheckBox();
    }
}
