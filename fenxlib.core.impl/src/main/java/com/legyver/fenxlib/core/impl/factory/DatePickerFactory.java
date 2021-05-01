package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.scene.control.DatePicker;

/**
 * Factory to create a DatePicker
 */
public class DatePickerFactory implements NodeFactory<DatePicker> {

	@Override
	public DatePicker makeNode(LocationContext locationContext) {
		DatePicker datePicker = new DatePicker();
		ApplicationContext.getComponentRegistry().register(locationContext, datePicker);
		return datePicker;
	}

}
