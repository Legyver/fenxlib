package com.legyver.fenxlib.skins.number.locator.query.bindings;

import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.locator.query.bindings.AbstractBindingFactory;
import com.legyver.fenxlib.skins.number.SkinnableNumberField;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.util.Optional;

public interface SkinnableNumberFieldBindingFactory extends AbstractBindingFactory {

	default void bindTextField(ObjectProperty<BigDecimal> property, ComponentQuery.RegionQueryBuilder query, String named) {
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
