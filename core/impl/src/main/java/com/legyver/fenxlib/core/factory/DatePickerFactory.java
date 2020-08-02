package com.legyver.fenxlib.core.factory;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.control.DatePicker;

public class DatePickerFactory implements NodeFactory<DatePicker> {

	@Override
	public DatePicker makeNode(LocationContext locationContext) {
		DatePicker datePicker = new DatePicker();
		ApplicationContext.getComponentRegistry().register(locationContext, datePicker);
		return datePicker;
	}

}
