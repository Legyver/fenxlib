package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.Button;

/**
 * Factory to create a Button control
 */
public class ButtonFactory implements NodeFactory<Button> {
    /**
     * Constructor parameter to specify text for the button
     */
    public static final String CONSTRUCTOR_PARAM_TEXT = "text";
    /**
     * Constructor parameter to specify if the button is a cancel button
     */
    public static final String CONSTRUCTOR_PARAM_IS_CANCEL_BUTTON = "cancelButton";
    /**
     * Constructor parameter to specify if the button is the default button
     */
    public static final String CONSTRUCTOR_PARAM_IS_DEFAULT_BUTTON = "defaultButton";

    private final String text;
    private final BooleanAdapter cancelButton;
    private final BooleanAdapter defaultButton;

    /**
     * Construct a Button.
     * @param text the text for the button
     * @param cancelButton true if the button is a cancel button
     * @param defaultButton true if the button is a default button
     */
    public ButtonFactory(String text, Boolean cancelButton, Boolean defaultButton) {
        this.text = text;
        this.cancelButton = new BooleanAdapter(cancelButton);
        this.defaultButton = new BooleanAdapter(defaultButton);
    }

    @Override
    public Button makeNode(LocationContext locationContext) throws CoreException {
        Button button = makeButton();
        button.setText(text);
        cancelButton.setNotNull(flag -> button.setCancelButton(flag));
        defaultButton.setNotNull(flag -> button.setDefaultButton(flag));

        Fenxlib.register(locationContext, button);
        return button;
    }

    /**
     * Factory method to instantiate a Button.
     * @return a javafx Button by default, override if you need something else
     */
    protected Button makeButton() {
        return new Button();
    }
}
