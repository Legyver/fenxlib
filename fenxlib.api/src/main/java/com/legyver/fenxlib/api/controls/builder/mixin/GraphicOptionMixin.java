package com.legyver.fenxlib.api.controls.builder.mixin;

import com.legyver.fenxlib.api.controls.builder.BaseControlBuilder;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;
import javafx.scene.Node;

/**
 * Mixin to specify a graphic.
 * To use this mixin, the builder must have a field of type Node called "graphic".
 *
 * @param <T> the type of the builder using this mixin.
 */
public interface GraphicOptionMixin<T extends BaseControlBuilder> extends OptionMixin<T> {
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
