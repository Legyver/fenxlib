package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.factory.adapters.BooleanAdapter;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ButtonOptions;
import javafx.scene.control.Button;

/**
 * Factory to create a Button control
 */
public class ButtonFactory<M extends Button> implements NodeFactory<Button, ButtonOptions> {

    @Override
    public Button makeNode(LocationContext locationContext, ButtonOptions options) throws CoreException {
        Button button = makeButton();
        button.setText(options.getText());
        new BooleanAdapter(options.getCancelButton()).setNotNull(flag -> button.setCancelButton(flag));
        new BooleanAdapter(options.getDefaultButton()).setNotNull(flag -> button.setDefaultButton(flag));

        Fenxlib.register(locationContext.decorateWith(options.getName()), button);
        return button;
    }

    @Override
    public ButtonOptions newOptions() {
        return new ButtonOptions();
    }

    /**
     * Factory method to instantiate a Button.
     *
     * @return a javafx Button by default, override if you need something else
     */
    protected Button makeButton() {
        return new Button();
    }
}
