package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

/**
 * Factory to create a TextField
 * @param <T> The Type of the TextField
 */
public class TextFieldFactory<T extends TextField> implements StyleableFactory<T> {

	/**
	 * Constructor parameter to bind the TextField to.
	 */
	public static final String BIND_TO = "bind_to";
	/**
	 * Constructor parameter to set as the default value for the TextField.
	 */
	public static final String DEFAULT_TEXT = "defaultText";
	/**
	 * Constructor parameter bind the editable control on the text field to.
	 */
	public static final String BIND_TO_EDITABLE = "bind_to_editable";
	/**
	 * Constructor parameter to indicate if the text field is editable
	 */
	public static final String IS_EDITABLE = "is_editable";

	private final BooleanProperty isEditable;
	private final StringProperty textProperty;
	private final String defaultText;

	/**
	 * Construct a TextFieldFactory that creates a TextField
	 * @param isEditable flag that determines if the field accepts text input
	 */
	public TextFieldFactory(boolean isEditable) {
		this.isEditable = new SimpleBooleanProperty();
		this.isEditable.setValue(isEditable);
		this.textProperty = null;
		this.defaultText = null;
	}

	/**
	 * Construct a TextFieldFactory that creates an editable JFXTextField
	 * @param textProperty the text property to bind to (if any)
	 * @param defaultText the default text (if any)
	 * @param isEditableProperty property to bind editability to
	 * @param isEditable flag that determines if a field is editable
	 */
	public TextFieldFactory(StringProperty textProperty, String defaultText, BooleanProperty isEditableProperty, Boolean
			isEditable) {
		this.textProperty = textProperty != null ? textProperty : new SimpleStringProperty();
		this.defaultText = defaultText;
		this.isEditable = isEditableProperty != null ? isEditableProperty : new SimpleBooleanProperty();
		if (isEditable != null) {
			this.isEditable.setValue(isEditable);
		}
	}

	@Override
	public T makeNode(LocationContext locationContext) throws CoreException {
		T textField = (T) makeTextField();

		ApplicationContext.getComponentRegistry().register(locationContext, textField);
		textField.editableProperty().bindBidirectional(isEditable);
		return textField;
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
