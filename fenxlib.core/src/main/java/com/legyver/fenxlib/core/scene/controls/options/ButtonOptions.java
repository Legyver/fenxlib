package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.GraphicMixin;
import com.legyver.fenxlib.core.controls.builder.ToolTipTextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * Options for a JavaFX Button
 */
public class ButtonOptions extends BaseControlBuilder<ButtonOptions> implements StyleableControlOptions<Button>,
        TextMixin<ButtonOptions>,
        ToolTipTextMixin<ButtonOptions>,
        GraphicMixin<ButtonOptions> {
    private String text;
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
