package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.DoubleMaxOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.DoubleMinOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.DoubleValueOptionMixin;
import javafx.scene.control.Slider;

/**
 * Options for a JavaFX Slider
 */
public class SliderOptions extends BaseControlBuilder<SliderOptions> implements StyleableControlOptions<Slider>,
        DoubleMinOptionMixin<SliderOptions>,
        DoubleMaxOptionMixin<SliderOptions>,
        DoubleValueOptionMixin<SliderOptions> {
    private Double min;
    private Double max;
    private Double value;
}
