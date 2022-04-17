package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.control.DatePicker;

/**
 * Factory to create a DatePicker
 */
public class DatePickerFactory implements StyleableFactory<DatePicker> {

	@Override
	public DatePicker makeNode(LocationContext locationContext) {
		DatePicker datePicker = new DatePicker();
		ApplicationContext.getComponentRegistry().register(locationContext, datePicker);
		return datePicker;
	}

}
