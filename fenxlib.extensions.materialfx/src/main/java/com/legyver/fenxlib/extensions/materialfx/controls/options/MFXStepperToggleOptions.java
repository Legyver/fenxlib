package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.extensions.materialfx.controls.factory.MFXStepperToggleFactory;
import io.github.palexdev.materialfx.controls.MFXStepperToggle;
import javafx.scene.Node;

public class MFXStepperToggleOptions extends BaseControlBuilder<MFXStepperToggleOptions> implements StyleableControlOptions<MFXStepperToggle> {
    private String text;
    private Node icon;
    private Node content;

    public MFXStepperToggleOptions text(String text) {
        this.text = text;
        return me();
    }

    public String getText() {
        return text;
    }

    public MFXStepperToggleOptions icon(Node icon) {
        this.icon = icon;
        return me();
    }

    public Node getIcon() {
        return icon;
    }

    public MFXStepperToggleOptions content(Node content) {
        this.content = content;
        return me();
    }

    public Node getContent() {
        return content;
    }
}
