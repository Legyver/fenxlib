package com.legyver.fenxlib.api.controls.builder;

import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;
import javafx.scene.layout.Region;

import java.util.List;

/**
 * Mixin to handle supplying children of type region.
 * To use this mixin, the builder must have a "children" field of type List&lt;Region&gt;
 * @param <T> the type of the builder using this mixin
 */
public interface ChildrenRegionsMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Specify any children
     * @param children the children
     * @return the builder using this mixin
     */
    default T children(List<? extends Region> children) {
        new ReflectionOperator(builder()).setValue("children", children);
        return builder();
    }

    /**
     * Get the children
     * @return the children
     */
    default List<Region> getChildren() {
        return (List) new ReflectionOperator(builder()).getValue("children");
    }
}
