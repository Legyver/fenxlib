package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.NodeContentOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXStepperToggle;
import javafx.scene.Node;

/**
 * Options for a MFXStepperToggle control
 */
public class MFXStepperToggleOptions extends BaseControlBuilder<MFXStepperToggleOptions> implements StyleableControlOptions<MFXStepperToggle>,
        TextOptionMixin<MFXStepperToggleOptions>,
        NodeContentOptionMixin<MFXStepperToggleOptions> {
    private String text;
    private Object[] textArgs;
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
