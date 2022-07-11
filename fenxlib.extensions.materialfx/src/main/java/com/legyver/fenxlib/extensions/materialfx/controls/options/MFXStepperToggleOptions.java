package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.NodeContentMixin;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.extensions.materialfx.controls.factory.MFXStepperToggleFactory;
import io.github.palexdev.materialfx.controls.MFXStepperToggle;
import javafx.scene.Node;

/**
 * Options for a MFXStepperToggle control
 */
public class MFXStepperToggleOptions extends BaseControlBuilder<MFXStepperToggleOptions> implements StyleableControlOptions<MFXStepperToggle>,
        TextMixin<MFXStepperToggleOptions>,
        NodeContentMixin<MFXStepperToggleOptions> {
    private String text;
    private Node icon;
    private Node content;

    /**
     * Specify the icon to be used in the stepper toggle
     * @param icon the icon
     * @return this builder
     */
    public MFXStepperToggleOptions icon(Node icon) {
        this.icon = icon;
        return me();
    }

    /**
     * Get the icon to be used in the stepper toggle
     * @return the icon
     */
    public Node getIcon() {
        return icon;
    }

}
