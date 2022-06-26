package com.legyver.fenxlib.extensions.materialfx.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.extensions.materialfx.controls.options.MFXTooltipOptions;
import io.github.palexdev.materialfx.controls.MFXTooltip;
import javafx.scene.Node;

import static java.awt.SystemColor.desktop;
import static java.awt.SystemColor.text;

/**
 * Factory to produce MFXTooltips
 */
public class MFXTooltipFactory implements StyleableFactory<MFXTooltip, MFXTooltipOptions> {

    @Override
    public MFXTooltip makeNode(LocationContext locationContext, MFXTooltipOptions options) throws CoreException {
        MFXTooltip tooltip;
        if (options.getText() == null) {
            tooltip = new MFXTooltip(options.getOwner());
        } else {
            tooltip = new MFXTooltip(options.getOwner(), options.getText());
        }
        return tooltip;
    }

    @Override
    public MFXTooltipOptions newOptions() {
        return new MFXTooltipOptions();
    }
}
