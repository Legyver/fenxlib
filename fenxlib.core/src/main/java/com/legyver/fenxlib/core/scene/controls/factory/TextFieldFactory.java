package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.scene.controls.options.TextFieldOptions;
import javafx.scene.control.TextField;

/**
 * Factory to create a TextField
 */
public class TextFieldFactory implements NodeFactory<TextField, TextFieldOptions> {

	@Override
	public TextField makeNode(LocationContext locationContext, TextFieldOptions options) throws CoreException {
		TextField textControl = makeTextField();
		textControl.setPromptText(options.getPromptText());

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
	public TextFieldOptions newOptions() {
		return new TextFieldOptions();
	}

	/**
	 * Factory method to create a TextField.
	 * By default, returns a plain javafx {@link TextField}. Override if you want something else.
	 * @return a new TextField
	 */
	protected TextField makeTextField() {
		return new TextField();
	}

}
