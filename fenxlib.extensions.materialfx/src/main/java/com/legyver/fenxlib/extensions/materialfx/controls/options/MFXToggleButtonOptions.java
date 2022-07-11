package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.GraphicMixin;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.scene.Node;

/**
 * Options for a MFXTableView control
 */
public class MFXToggleButtonOptions extends BaseControlBuilder<MFXToggleButtonOptions> implements StyleableControlOptions<MFXToggleButton>,
        TextMixin<MFXToggleButtonOptions>,
        GraphicMixin<MFXToggleButtonOptions> {
    private String text;
    private Node graphic;

}
