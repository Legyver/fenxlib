package com.legyver.fenxlib.core.impl.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.api.locator.query.bindings.AbstractBindingFactory;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Binds a DatePicker (locatable by a ComponentQuery and name) to an Observable LocalDate
 */
public interface DatePickerBindingFactory extends AbstractBindingFactory {

	default void bindDatePicker(ObjectProperty<LocalDate> property, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<DatePicker> picker = finalizeQuery(query, named).execute();
		LocalDate value = property.get();
		property.bind(picker.get().valueProperty());
		picker.get().setValue(value);
	}
}
