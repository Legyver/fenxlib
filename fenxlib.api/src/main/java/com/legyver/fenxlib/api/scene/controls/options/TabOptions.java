package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.mixin.NodeContentOptionMixin;
import com.legyver.fenxlib.api.controls.builder.mixin.TextOptionMixin;
import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 * Options for a JavaFX Tab
 */
public class TabOptions extends BaseControlBuilder<TabOptions> implements StyleableControlOptions<Tab>,
        TextOptionMixin<TabOptions>,
        NodeContentOptionMixin<TabOptions> {
    private String text;
    private Object[] textArgs;
    private Node content;
}
