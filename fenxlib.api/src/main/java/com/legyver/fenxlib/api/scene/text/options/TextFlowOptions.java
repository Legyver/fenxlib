package com.legyver.fenxlib.api.scene.text.options;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.controls.builder.TextMixin;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.scene.Node;
import javafx.scene.text.TextFlow;

import java.util.List;

/**
 * Options for a JavaFX TextFlow
 */
public class TextFlowOptions extends BaseControlBuilder<TextFlowOptions> implements StyleableControlOptions<TextFlow>,
        TextMixin<TextFlowOptions> {
    private String text;
    private List<Node> children;

    /**
     * Specify children for this control
     * @param children control children
     * @return this builder
     */
    public TextFlowOptions children(List<Node> children) {
        this.children = children;
        return me();
    }

    /**
     * Get the children
     * @return the children
     */
    public List<Node> getChildren() {
        return children;
    }
}
