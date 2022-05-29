package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

/**
 * Factory to create a TextField
 * @param <T> The Type of the TextField
 */
public class TextFieldFactory<T extends TextField> extends AbstractInputTextControlsFactory<T> implements NodeFactory<T> {

	/**
	 * Construct a TextFieldFactory that creates a TextField
	 * @param textProperty the text property to bind to (if any)
	 * @param defaultText the default text (if any)
	 * @param isEditableProperty property to bind editability to
	 * @param isEditable flag that determines if a field is editable
	 */
	public TextFieldFactory(StringProperty textProperty, String defaultText, BooleanProperty isEditableProperty, Boolean isEditable) {
		super(textProperty, defaultText, isEditableProperty, isEditable);
	}

	@Override
	protected T makeInputControl() {
		return (T) makeTextField();
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
