package com.legyver.fenxlib.core.locator.query.bindings;

import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

import java.util.Optional;

public interface TextFieldBindingFactory extends AbstractBindingFactory {
	default void bindTextField(StringProperty property, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<TextField> text = finalizeQuery(query, named).execute();
		String value = property.get();
		property.bindBidirectional(text.get().textProperty());
		if (value == null) {
			text.get().clear();
		} else {
			text.get().setText(value);
		}
	}
}
