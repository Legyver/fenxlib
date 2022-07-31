package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.ColorPickerOptions;
import javafx.scene.control.ColorPicker;

/**
 * Factory to create a ColorPicker control
 */
public class ColorPickerFactory implements NodeFactory<ColorPicker, ColorPickerOptions> {

    @Override
    public ColorPicker makeNode(LocationContext locationContext, ColorPickerOptions options) throws CoreException {
        ColorPicker colorPicker = makeColorPicker();
        Fenxlib.register(locationContext.decorateWith(options.getName()), colorPicker);
        return colorPicker;
    }

    @Override
    public ColorPickerOptions newOptions() {
        return new ColorPickerOptions();
    }

    /**
     * Factory method to instantiate a ColorPicker.
     * @return a javafx ColorPicker by default, override if you need something else
     */
    protected ColorPicker makeColorPicker() {
        return new ColorPicker();
    }
}
