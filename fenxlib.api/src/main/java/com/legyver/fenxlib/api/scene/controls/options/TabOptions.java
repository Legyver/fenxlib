package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.NodeContentMixin;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import javafx.scene.Node;
import javafx.scene.control.Tab;

/**
 * Options for a JavaFX Tab
 */
public class TabOptions extends BaseControlBuilder<TabOptions> implements StyleableControlOptions<Tab>,
        TextMixin<TabOptions>,
        NodeContentMixin<TabOptions> {
    private String text;
    private Node content;
}
