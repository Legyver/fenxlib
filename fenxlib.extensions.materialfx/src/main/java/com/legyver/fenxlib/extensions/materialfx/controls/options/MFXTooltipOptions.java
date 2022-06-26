package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTooltip;
import javafx.scene.Node;

public class MFXTooltipOptions extends BaseControlBuilder<MFXTooltipOptions> implements StyleableControlOptions<MFXTooltip> {
    private Node owner;
    private String text;

    public MFXTooltipOptions owner(Node owner) {
        this.owner = owner;
        return me();
    }

    public Node getOwner() {
        return owner;
    }

    public MFXTooltipOptions text(String text) {
        this.text = text;
        return me();
    }

    public String getText() {
        return text;
    }
}
