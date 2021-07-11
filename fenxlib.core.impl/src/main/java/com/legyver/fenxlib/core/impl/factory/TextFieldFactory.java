package com.legyver.fenxlib.core.impl.factory;

import com.jfoenix.controls.JFXTextField;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.factory.NodeInstantiator;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;

/**
 * Factory to create a TextField
 * @param <T> The Type of the TextField
 */
public class TextFieldFactory<T extends TextField> implements NodeFactory<T> {
	private final BooleanProperty isEditable = new SimpleBooleanProperty();
	private final NodeInstantiator<T> instantiator;

	/**
	 * Construct a TextFieldFactory that creates an editable TextField based on the instantiator
	 * @param isEditable flag that determines if the field accepts text input
	 * @param instantiator instantiator that creates the TextField
	 */
	public TextFieldFactory(boolean isEditable, NodeInstantiator<T> instantiator) {
		this.isEditable.setValue(isEditable);
		this.instantiator = instantiator;
	}

	/**
	 * Construct a TextFieldFactory that creates an editable JFXTextField
	 * @param isEditable flag that determines if the field accepts text input
	 */
	public TextFieldFactory(boolean isEditable) {
		this(isEditable, () -> (T) new JFXTextField());
	}

	@Override
	public T makeNode(LocationContext locationContext) {
		T textField = instantiator.newInstance();

		ApplicationContext.getComponentRegistry().register(locationContext, textField);
		textField.editableProperty().bindBidirectional(isEditable);
		return textField;
	}
}
