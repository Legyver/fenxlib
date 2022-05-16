package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.PasswordField;

/**
 * Factory to create a PasswordField control
 */
public class PasswordFieldFactory implements NodeFactory<PasswordField> {
    @Override
    public PasswordField makeNode(LocationContext locationContext) throws CoreException {
        PasswordField passwordField = makePasswordField();
        Fenxlib.register(locationContext, passwordField);
        return passwordField;
    }

    /**
     * Factory method to instantiate a PasswordField.
     * @return a javafx PasswordField by default, override if you need something else
     */
    protected PasswordField makePasswordField() {
        return new PasswordField();
    }
}
