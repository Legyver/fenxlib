package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ColorPicker;

/**
 * Factory to create a ColorPicker control
 */
public class ColorPickerFactory implements NodeFactory<ColorPicker> {
    @Override
    public ColorPicker makeNode(LocationContext locationContext) throws CoreException {
        ColorPicker colorPicker = makeColorPicker();
        Fenxlib.register(locationContext, colorPicker);
        return colorPicker;
    }

    /**
     * Factory method to instantiate a ColorPicker.
     * @return a javafx ColorPicker by default, override if you need something else
     */
    protected ColorPicker makeColorPicker() {
        return new ColorPicker();
    }
}
