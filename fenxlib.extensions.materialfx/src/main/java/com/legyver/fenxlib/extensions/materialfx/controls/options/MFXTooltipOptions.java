package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXTooltip;
import javafx.scene.Node;

/**
 * Options for a MFXTooltip control
 */
public class MFXTooltipOptions extends BaseControlBuilder<MFXTooltipOptions> implements StyleableControlOptions<MFXTooltip>,
        TextMixin<MFXTooltipOptions> {
    private Node owner;
    private String text;

    /**
     * Specify the node to install the tooltip on
     * @param owner the node to install the tooltip on
     * @return this builder
     */
    public MFXTooltipOptions owner(Node owner) {
        this.owner = owner;
        return me();
    }

    /**
     * Get the owner
     * @return the node to install the tooltip on
     */
    public Node getOwner() {
        return owner;
    }
}
