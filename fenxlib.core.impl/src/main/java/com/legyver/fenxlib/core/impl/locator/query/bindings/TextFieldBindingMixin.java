package com.legyver.fenxlib.core.impl.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.IRegionDiscriminator;
import com.legyver.fenxlib.core.api.locator.query.bindings.AbstractBindingMixin;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextField;

import java.util.Optional;

/**
 * Facilitates binding of text fields to a StringProperty
 */
public interface TextFieldBindingMixin extends AbstractBindingMixin {
	/**
	 * Bind the text field to a specified property
	 * @param property the property to use as the value source for the text field
	 * @param query the query locating the text field
	 * @param named the (optional) name of the text field
	 */
	default void bindTextField(StringProperty property, IRegionDiscriminator query, String named) {
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
