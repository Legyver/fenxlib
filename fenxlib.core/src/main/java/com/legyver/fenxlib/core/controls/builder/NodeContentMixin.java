package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.scene.Node;

public interface NodeContentMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    default T content(Node content) {
        new ReflectionOperator(builder()).setValue("content", content);
        return builder();
    }

    default Node getContent() {
        return (Node) new ReflectionOperator(builder()).getValue("content");
    }
}
