package com.legyver.fenxlib.factory;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TextField;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.util.GuiUtil;

public class TextFieldFactory implements NodeFactory<TextField> {
	private final BooleanProperty isEditable = new SimpleBooleanProperty();

	public TextFieldFactory(boolean isEditable) {
		this.isEditable.setValue(isEditable);
	}

	@Override
	public TextField makeNode(LocationContext locationContext) {
		TextField textField = new TextField();
		GuiUtil.getComponentRegistry().register(locationContext, textField);
		textField.editableProperty().bindBidirectional(isEditable);
		return textField;
	}
}
