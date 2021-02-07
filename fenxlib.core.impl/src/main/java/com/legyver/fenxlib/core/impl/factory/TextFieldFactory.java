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
 */
public class TextFieldFactory implements NodeFactory<TextField> {
	private final BooleanProperty isEditable = new SimpleBooleanProperty();
	private final NodeInstantiator<JFXTextField> instantiator;

	public TextFieldFactory(boolean isEditable, NodeInstantiator<JFXTextField> instantiator) {
		this.isEditable.setValue(isEditable);
		this.instantiator = instantiator;
	}

	public TextFieldFactory(boolean isEditable) {
		this(isEditable, () -> new JFXTextField());
	}

	@Override
	public TextField makeNode(LocationContext locationContext) {
		JFXTextField textField = instantiator.newInstance();

		ApplicationContext.getComponentRegistry().register(locationContext, textField);
		textField.editableProperty().bindBidirectional(isEditable);
		return textField;
	}
}
