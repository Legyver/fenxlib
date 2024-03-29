package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.mixin.NodeContentOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

/**
 * Options for a JavaFX ScrollPane
 */
public class ScrollPaneOptions extends BaseControlBuilder<ScrollPaneOptions> implements StyleableControlOptions<ScrollPane>,
        NodeContentOptionMixin<ScrollPaneOptions> {
    private Node content;

}
