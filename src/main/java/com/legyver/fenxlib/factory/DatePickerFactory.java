package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.util.GuiUtil;
import javafx.scene.control.DatePicker;

public class DatePickerFactory implements NodeFactory<DatePicker> {

	@Override
	public DatePicker makeNode(LocationContext locationContext) {
		DatePicker datePicker = new DatePicker();
		GuiUtil.getComponentRegistry().register(locationContext, datePicker);
		return datePicker;
	}

}
