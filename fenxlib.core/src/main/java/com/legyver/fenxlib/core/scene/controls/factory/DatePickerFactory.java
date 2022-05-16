package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.DatePicker;

/**
 * Factory to create a DatePicker control
 */
public class DatePickerFactory implements NodeFactory<DatePicker> {
    @Override
    public DatePicker makeNode(LocationContext locationContext) throws CoreException {
        DatePicker datePicker = makeDatePicker();
        Fenxlib.register(locationContext, datePicker);
        return datePicker;
    }

    /**
     * Factory method to instantiate a DatePicker.
     * @return a javafx DatePicker by default, override if you need something else
     */
    protected DatePicker makeDatePicker() {
        return new DatePicker();
    }
}
