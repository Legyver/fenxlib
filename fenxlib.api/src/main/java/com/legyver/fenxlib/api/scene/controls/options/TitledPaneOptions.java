package com.legyver.fenxlib.api.scene.controls.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.NodeContentMixin;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
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
