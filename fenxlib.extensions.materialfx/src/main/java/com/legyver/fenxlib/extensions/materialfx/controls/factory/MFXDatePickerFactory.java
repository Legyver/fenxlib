package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXDatePickerOptions;
import io.github.palexdev.materialfx.controls.MFXDatePicker;

/**
 * Factory to create a MaterialFX DatePicker
 */
public class MFXDatePickerFactory implements NodeFactory<MFXDatePicker, MFXDatePickerOptions>  {

    @Override
    public MFXDatePicker makeNode(LocationContext locationContext, MFXDatePickerOptions options) throws CoreException {
        MFXDatePicker datePicker = new MFXDatePicker();
        Fenxlib.register(locationContext.decorateWith(options.getName()), datePicker);
        return datePicker;
    }

    @Override
    public MFXDatePickerOptions newOptions() {
        return new MFXDatePickerOptions();
    }

}
