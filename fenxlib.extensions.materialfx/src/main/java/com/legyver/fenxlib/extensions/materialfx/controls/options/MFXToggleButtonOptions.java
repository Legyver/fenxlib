package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.GraphicOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.scene.Node;

/**
 * Options for a MFXTableView control
 */
public class MFXToggleButtonOptions extends BaseControlBuilder<MFXToggleButtonOptions> implements StyleableControlOptions<MFXToggleButton>,
        TextOptionMixin<MFXToggleButtonOptions>,
        GraphicOptionMixin<MFXToggleButtonOptions> {
    private String text;
    private Object[] textArgs;
    private Node graphic;

}
