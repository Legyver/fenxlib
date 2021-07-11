package com.legyver.fenxlib.skins.number.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.IRegionDiscriminator;
import com.legyver.fenxlib.core.api.locator.query.bindings.AbstractBindingFactory;
import com.legyver.fenxlib.skins.number.SkinnableNumberField;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Mixin to allow for binding of a SkinnableNumberField to a BigDecimal ObjectProperty
 */
public interface SkinnableNumberFieldBindingFactory extends AbstractBindingFactory {

	/**
	 * Bind the SkinnableNumberField to a BigDecimal object property
	 * @param property the property to bind to
	 * @param query the query that will locate the SkinnableNumberField
	 * @param named the name of the skinnable number field
	 */
	default void bindTextField(ObjectProperty<BigDecimal> property, IRegionDiscriminator query, String named) {
		Optional<TextField> text = finalizeQuery(query, named).execute();
		TextField textField = text.get();
		if (textField instanceof SkinnableNumberField) {
			SkinnableNumberField numberField = (SkinnableNumberField) textField;
			BigDecimal value = property.get();
			property.bindBidirectional(numberField.valueProperty());
			numberField.setValue(value);
		}
	}
}
