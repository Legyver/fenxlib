package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.scene.Node;

/**
 * Mixin to support options that have a property for content of type Node.
 * To use this mixin, your builder must have a field of type Node called "content".
 *
 * @param <T> the type of the using builder
 */
public interface NodeContentMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    /**
     * Specify content to use.
     * @param content the content to use
     * @return this builder
     */
    default T content(Node content) {
        new ReflectionOperator(builder()).setValue("content", content);
        return builder();
    }

    /**
     * Get the content to use.
     * @return this content
     */
    default Node getContent() {
        return (Node) new ReflectionOperator(builder()).getValue("content");
    }
}
