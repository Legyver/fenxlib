package com.legyver.fenxlib.core.scene.controls.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.NodeContentMixin;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;

/**
 * Options for a JavaFX TitledPane
 */
public class TitledPaneOptions extends BaseControlBuilder<TitledPaneOptions> implements StyleableControlOptions<TitledPane>,
        TextMixin<TitledPaneOptions>,
        NodeContentMixin<TitledPaneOptions> {
    private String text;
    private Node content;
}
