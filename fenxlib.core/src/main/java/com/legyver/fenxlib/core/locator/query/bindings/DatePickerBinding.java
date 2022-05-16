package com.legyver.fenxlib.core.locator.query.bindings;

import com.legyver.fenxlib.api.locator.query.IRegionDiscriminator;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Binds a DatePicker (locatable by a ComponentQuery and name) to an Observable LocalDate
 */
public class DatePickerBinding extends BaseBinding {

	/**
	 * Bind a date-picker value to a LocalDate property
	 * @param property the property to bind as the value source
	 * @param query the query that identifies the date-picker
	 * @param named the (optional) name of the date-picker
	 */
	public static void bindDatePicker(ObjectProperty<LocalDate> property, IRegionDiscriminator query, String named) {
		Optional<DatePicker> picker = finalizeQuery(query, named).execute();
		LocalDate value = property.get();
		property.bind(picker.get().valueProperty());
		picker.get().setValue(value);
	}
}
