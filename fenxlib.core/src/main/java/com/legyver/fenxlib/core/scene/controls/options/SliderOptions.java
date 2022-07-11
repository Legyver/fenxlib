package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.DoubleMaxMixin;
import com.legyver.fenxlib.core.controls.builder.DoubleMinMixin;
import com.legyver.fenxlib.core.controls.builder.DoubleValueMixin;
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
