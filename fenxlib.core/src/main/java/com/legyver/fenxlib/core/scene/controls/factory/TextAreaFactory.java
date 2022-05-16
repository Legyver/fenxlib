package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.TextArea;

/**
 * Factory to create a TextArea control
 */
public class TextAreaFactory implements StyleableFactory<TextArea> {
    @Override
    public TextArea makeNode(LocationContext locationContext) throws CoreException {
        TextArea textArea = makeTextArea();
        Fenxlib.register(locationContext, textArea);
        return textArea;
    }

    /**
     * Factory method to instantiate a TextArea.
     * @return a javafx TextArea by default, override if you need something else
     */
    protected TextArea makeTextArea() {
        return new TextArea();
    }
}
