package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import io.github.palexdev.materialfx.controls.MFXTooltip;
import javafx.scene.Node;

/**
 * Factory to produce MFXTooltips
 */
public class MFXTooltipFactory implements StyleableFactory<MFXTooltip> {
    /**
     * Param to specify the node the tooltip is associated with
     */
    public static final String CONSTRUCTOR_PARAM_OWNER = "owner";
    /**
     * Param to specify the tooltip text
     */
    public static final String CONSTRUCTOR_PARAM_TEXT = "text";
    private final Node owner;
    private final String text;

    /**
     * Construct a factory to produce a MFXTooltip
     * @param owner the node the tooltip is associated with
     * @param text the text of the tooltip
     */
    public MFXTooltipFactory(Node owner, String text) {
        this.owner = owner;
        this.text = text;
    }

    @Override
    public MFXTooltip makeNode(LocationContext locationContext) throws CoreException {
        MFXTooltip tooltip;
        if (text == null) {
            tooltip = new MFXTooltip(owner);
        } else {
            tooltip = new MFXTooltip(owner, text);
        }
        return tooltip;
    }
}
