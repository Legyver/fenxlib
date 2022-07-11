package com.legyver.fenxlib.extensions.materialfx.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.NodeContentMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import io.github.palexdev.materialfx.controls.MFXMagnifierPane;
import javafx.scene.Node;

/**
 * Options for a MFXMagnifierPane control
 */
public class MFXMagnifierPaneOptions extends BaseControlBuilder<MFXMagnifierPaneOptions> implements StyleableControlOptions<MFXMagnifierPane>,
        NodeContentMixin<MFXMagnifierPaneOptions> {
    private Node content;
}
