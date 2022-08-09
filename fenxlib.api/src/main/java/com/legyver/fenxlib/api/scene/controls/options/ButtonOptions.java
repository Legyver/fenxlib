package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.mixin.GraphicOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.ToolTipTextOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Options for a JavaFX Button
 */
public class ButtonOptions extends BaseControlBuilder<ButtonOptions> implements StyleableControlOptions<Button>,
        TextOptionMixin<ButtonOptions>,
        ToolTipTextOptionMixin<ButtonOptions>,
        GraphicOptionMixin<ButtonOptions> {
    private String text;
    private Object[] textArgs;
    private String toolTipText;
    private Node graphic;
    private Boolean cancelButton;
    private Boolean defaultButton;

    /**
     * Specify if the button is a cancel button
     * @param cancelButton true if the button is a cancel button
     * @return this builder
     */
    public ButtonOptions cancelButton(Boolean cancelButton) {
        this.cancelButton = cancelButton;
        return me();
    }

    /**
     * Specify if the button is selected by default
     * @param defaultButton true if the button should be the default button
     * @return this builder
     */
    public ButtonOptions defaultButton(Boolean defaultButton) {
        this.defaultButton = defaultButton;
        return me();
    }

    /**
     * Get the cancel button flag
     * @return the cancel button flag
     */
    public Boolean getCancelButton() {
        return cancelButton;
    }

    /**
     * Get the default button flag
     * @return the default button flag
     */
    public Boolean getDefaultButton() {
        return defaultButton;
    }
}
