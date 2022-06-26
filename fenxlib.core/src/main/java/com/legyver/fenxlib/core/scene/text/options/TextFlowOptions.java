package com.legyver.fenxlib.core.scene.text.options;

import com.legyver.fenxlib.core.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.core.controls.builder.TextMixin;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.scene.text.factory.TextFlowFactory;
import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

public class TextFlowOptions extends BaseControlBuilder<TextFlowOptions> implements StyleableControlOptions<TextFlow>,
        TextMixin<TextFlowOptions> {
    private String text;
    private List<Node> children;

    public TextFlowOptions children(List<Node> children) {
        this.children = children;
        return me();
    }

    public List<Node> getChildren() {
        return children;
    }
}
