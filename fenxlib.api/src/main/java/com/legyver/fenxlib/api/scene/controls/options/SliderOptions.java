package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.DoubleMaxMixin;
import com.legyver.fenxlib.api.controls.builder.DoubleMinMixin;
import com.legyver.fenxlib.api.controls.builder.DoubleValueMixin;
import javafx.scene.control.Slider;

/**
 * Options for a JavaFX Slider
 */
public class SliderOptions extends BaseControlBuilder<SliderOptions> implements StyleableControlOptions<Slider>,
        DoubleMinMixin<SliderOptions>,
        DoubleMaxMixin<SliderOptions>,
        DoubleValueMixin<SliderOptions> {
    private Double min;
    private Double max;
    private Double value;
}
