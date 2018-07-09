package com.legyver.fenxlib.factory;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.util.GuiUtil;

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

		GuiUtil.getComponentRegistry().register(locationContext, textField);
		textField.editableProperty().bindBidirectional(isEditable);
		return textField;
	}
}
