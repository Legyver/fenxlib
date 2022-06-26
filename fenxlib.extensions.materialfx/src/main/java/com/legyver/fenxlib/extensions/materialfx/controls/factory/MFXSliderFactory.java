package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXSliderOptions;
import io.github.palexdev.materialfx.controls.MFXSlider;

/**
 * Factory to produce an MFXSlider
 */
public class MFXSliderFactory implements NodeFactory<MFXSlider, MFXSliderOptions> {

    @Override
    public MFXSlider makeNode(LocationContext locationContext, MFXSliderOptions options) throws CoreException {
        MFXSlider slider;
        Double min = options.getMin();
        Double max = options.getMax();
        Double initialValue = options.getValue();
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

    @Override
    public MFXSliderOptions newOptions() {
        return new MFXSliderOptions();
    }

    public static class Options extends BaseControlBuilder<Options> {
        private Double min;
        private Double max;
        private Double initialValue;

        public Options min(Double min) {
            this.min = min;
            return me();
        }

        public Double getMin() {
            return min;
        }

        public Options max(Double max) {
            this.max = max;
            return me();
        }

        public Double getMax() {
            return max;
        }

        public Options initialValue(Double initialValue) {
            this.initialValue = initialValue;
            return me();
        }

        public Double getInitialValue() {
            return initialValue;
        }
    }
}
