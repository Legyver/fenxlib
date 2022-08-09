package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.DoubleMaxOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.DoubleMinOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.DoubleValueOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXSlider;

/**
 * Options for a MFXSlider control
 */
public class MFXSliderOptions extends BaseControlBuilder<MFXSliderOptions> implements StyleableControlOptions<MFXSlider>,
        DoubleMaxOptionMixin<MFXSliderOptions>,
        DoubleMinOptionMixin<MFXSliderOptions>,
        DoubleValueOptionMixin<MFXSliderOptions> {
    private Double min;
    private Double max;
    private Double value;
}
