package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.PasswordFieldOptions;
import javafx.scene.control.PasswordField;

/**
 * Factory to create a PasswordField control
 */
public class PasswordFieldFactory implements NodeFactory<PasswordField, PasswordFieldOptions> {

    @Override
    public PasswordField makeNode(LocationContext locationContext, PasswordFieldOptions options) throws CoreException {
        PasswordField passwordField = makePasswordField();
        passwordField.setPromptText(options.getPromptText());
        Fenxlib.register(locationContext.decorateWith(options.getName()), passwordField);
        return passwordField;
    }

    @Override
    public PasswordFieldOptions newOptions() {
        return new PasswordFieldOptions();
    }

    /**
     * Factory method to instantiate a PasswordField.
     * @return a javafx PasswordField by default, override if you need something else
     */
    protected PasswordField makePasswordField() {
        return new PasswordField();
    }
}
