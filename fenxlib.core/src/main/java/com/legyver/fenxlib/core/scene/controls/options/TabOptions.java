package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.NodeContentMixin;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
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
