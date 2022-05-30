package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import io.github.palexdev.materialfx.controls.MFXSlider;

/**
 * Factory to produce an MFXSlider
 */
public class MFXSliderFactory implements NodeFactory<MFXSlider> {
    /**
     * Constructor param to specify the minimum value for a MFXSlider
     */
    public static final String CONSTRUCTOR_PARAM_MIN = "min";
    /**
     * Constructor param to specify the maximum value for a MFXSlider
     */
    public static final String CONSTRUCTOR_PARAM_MAX = "max";
    /**
     * Constructor param to specify the initial value for a MFXSlider
     */
    public static final String CONSTRUCTOR_PARAM_INITIAL_VALUE = "initialValue";

    private final Double min;
    private final Double max;
    private final Double initialValue;

    /**
     * Construct a factory to produce a MFXSlider
     * @param min the minimum value
     * @param max the maximum value
     * @param initialValue the initial value
     */
    public MFXSliderFactory(Double min, Double max, Double initialValue) {
        this.min = min;
        this.max = max;
        this.initialValue = initialValue;
    }

    @Override
    public MFXSlider makeNode(LocationContext locationContext) throws CoreException {
        MFXSlider slider;
        if (min != null && max != null && initialValue != null) {
            slider = new MFXSlider(min, max, initialValue);
        } else if (initialValue != null) {
            slider = new MFXSlider(initialValue);
        } else {
            slider = new MFXSlider();
        }
        Fenxlib.register(locationContext, slider);
        return slider;
    }
}
