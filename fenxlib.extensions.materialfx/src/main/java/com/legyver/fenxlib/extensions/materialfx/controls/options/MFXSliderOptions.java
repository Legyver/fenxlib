package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.DoubleMaxMixin;
import com.legyver.fenxlib.api.controls.builder.DoubleMinMixin;
import com.legyver.fenxlib.api.controls.builder.DoubleValueMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXSlider;

/**
 * Options for a MFXSlider control
 */
public class MFXSliderOptions extends BaseControlBuilder<MFXSliderOptions> implements StyleableControlOptions<MFXSlider>,
        DoubleMaxMixin<MFXSliderOptions>,
        DoubleMinMixin<MFXSliderOptions>,
        DoubleValueMixin<MFXSliderOptions> {
    private Double min;
    private Double max;
    private Double value;
}
