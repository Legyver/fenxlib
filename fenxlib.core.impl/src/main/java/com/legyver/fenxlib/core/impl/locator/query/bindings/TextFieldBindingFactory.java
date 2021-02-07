package com.legyver.fenxlib.core.impl.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.api.locator.query.bindings.AbstractBindingFactory;
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
