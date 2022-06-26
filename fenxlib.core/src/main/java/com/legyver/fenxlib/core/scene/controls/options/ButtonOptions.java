package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.GraphicMixin;
import com.legyver.fenxlib.core.controls.builder.ToolTipTextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

public class ButtonOptions extends BaseControlBuilder<ButtonOptions> implements StyleableControlOptions<Button>,
        TextMixin<ButtonOptions>,
        ToolTipTextMixin<ButtonOptions>,
        GraphicMixin<ButtonOptions> {
    private String text;
    private String toolTipText;
    private Node graphic;
    private Boolean cancelButton;
    private Boolean defaultButton;

    public ButtonOptions cancelButton(Boolean cancelButton) {
        this.cancelButton = cancelButton;
        return me();
    }

    public ButtonOptions defaultButton(Boolean defaultButton) {
        this.defaultButton = defaultButton;
        return me();
    }

    public Boolean getCancelButton() {
        return cancelButton;
    }

    public Boolean getDefaultButton() {
        return defaultButton;
    }
}
