package com.legyver.fenxlib.core.impl.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.IRegionDiscriminator;
import com.legyver.fenxlib.core.api.locator.query.bindings.AbstractBindingMixin;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Binds a DatePicker (locatable by a ComponentQuery and name) to an Observable LocalDate
 */
public interface DatePickerBindingMixin extends AbstractBindingMixin {

	/**
	 * Bind a date-picker value to a LocalDate property
	 * @param property the property to bind as the value source
	 * @param query the query that identifies the date-picker
	 * @param named the (optional) name of the date-picker
	 */
	default void bindDatePicker(ObjectProperty<LocalDate> property, IRegionDiscriminator query, String named) {
		Optional<DatePicker> picker = finalizeQuery(query, named).execute();
		LocalDate value = property.get();
		property.bind(picker.get().valueProperty());
		picker.get().setValue(value);
	}
}
