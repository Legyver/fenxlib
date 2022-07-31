package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.scene.controls.options.TextAreaOptions;
import javafx.scene.control.TextArea;

/**
 * Factory to create a TextArea control
 */
public class TextAreaFactory implements NodeFactory<TextArea, TextAreaOptions> {

    /**
     * Factory method to instantiate a TextArea.
     * @return a javafx TextArea by default, override if you need something else
     */
    protected TextArea makeTextArea() {
        return new TextArea();
    }

    @Override
    public TextArea makeNode(LocationContext locationContext, TextAreaOptions options) throws CoreException {
        TextArea textControl = makeTextArea();

        ApplicationContext.getComponentRegistry().register(locationContext, textControl);
        if (options.getEditableProperty() != null) {
            textControl.editableProperty().bindBidirectional(options.getEditableProperty());
        }
        if (options.isEditable() != null) {
            textControl.setEditable(options.isEditable());
        }

        if (options.getTextProperty() != null) {
            textControl.textProperty().bindBidirectional(options.getTextProperty());
        }

        if (options.getText() != null) {
            textControl.setText(options.getText());
        }

        return textControl;
    }

    @Override
    public TextAreaOptions newOptions() {
        return new TextAreaOptions();
    }
}
