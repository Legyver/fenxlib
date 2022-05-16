package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.CheckMenuItem;

/**
 * Factory to create a CheckMenuItem control
 */
public class CheckMenuItemFactory implements StyleableFactory<CheckMenuItem> {

    /**
     * Constructor parameter to specify text for the CheckMenuItem
     */
    public static final String CONSTRUCTOR_PARAM_TEXT = "text";
    /**
     * Constructor parameter to specify if the CheckMenuItem is selected
     */
    public static final String CONSTRUCTOR_PARAM_IS_SELECTED = "selected";
    /**
     * Constructor parameter to specify if the CheckMenuItem is disabled
     */
    public static final String CONSTRUCTOR_PARAM_IS_DISABLED = "disabled";

    private final String text;
    private final BooleanAdapter selected;
    private final BooleanAdapter disabled;

    /**
     * Construct a Button.
     * @param text the text for the CheckMenuItem
     * @param selected true if the CheckMenuItem is selected
     * @param disabled true if the CheckMenuItem is disabled
     */
    public CheckMenuItemFactory(String text, Boolean selected, Boolean disabled) {
        this.text = text;
        this.selected = new BooleanAdapter(selected);
        this.disabled = new BooleanAdapter(disabled);
    }

    @Override
    public CheckMenuItem makeNode(LocationContext locationContext) throws CoreException {
        CheckMenuItem checkMenuItem = makeCheckMenuItem();
        checkMenuItem.setText(text);
        selected.setNotNull(flag -> checkMenuItem.setSelected(flag));
        disabled.setNotNull(flag -> checkMenuItem.setDisable(flag));
        Fenxlib.register(locationContext, checkMenuItem);
        return checkMenuItem;
    }

    /**
     * Factory method to instantiate a CheckMenuItem.
     * @return a javafx CheckMenuItem by default, override if you need something else
     */
    protected CheckMenuItem makeCheckMenuItem() {
        return new CheckMenuItem();
    }
}
