package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.scene.Node;

/**
 * Mixin to specify a graphic.
 * To use this mixin, the builder must have a field of type Node called "graphic".
 *
 * @param <T> the type of the builder using this mixin.
 */
public interface GraphicMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
    /**
     * Specify the node to use as the graphic
     * @param graphic the graphic
     * @return the builder using this mixin
     */
    default T graphic(Node graphic) {
        new ReflectionOperator(builder()).setValue("graphic", graphic);
        return builder();
    }

    /**
     * Get the graphic
     * @return the graphic
     */
    default Node getGraphic() {
        return (Node) new ReflectionOperator(builder()).getValue("graphic");
    }
}
