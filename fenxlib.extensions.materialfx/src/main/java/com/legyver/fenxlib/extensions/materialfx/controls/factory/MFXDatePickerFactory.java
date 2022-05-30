package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXDatePicker;

/**
 * Factory to create a MaterialFX DatePicker
 */
public class MFXDatePickerFactory implements NodeFactory<MFXDatePicker>  {

    @Override
    public MFXDatePicker makeNode(LocationContext locationContext) throws CoreException {
        MFXDatePicker datePicker = new MFXDatePicker();
        Fenxlib.register(locationContext, datePicker);
        return datePicker;
    }
}
