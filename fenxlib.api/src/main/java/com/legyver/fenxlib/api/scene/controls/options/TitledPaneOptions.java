package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.NodeContentOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;

/**
 * Options for a JavaFX TitledPane
 */
public class TitledPaneOptions extends BaseControlBuilder<TitledPaneOptions> implements StyleableControlOptions<TitledPane>,
        TextOptionMixin<TitledPaneOptions>,
        NodeContentOptionMixin<TitledPaneOptions> {
    private String text;
    private Object[] textArgs;
    private Node content;
}
