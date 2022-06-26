package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;
import javafx.scene.layout.Region;

import java.util.List;

public interface ChildrenRegionsMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    default T children(List<? extends Region> children) {
        new ReflectionOperator(builder()).setValue("children", children);
        return builder();
    }

    default List<Region> getChildren() {
        return (List) new ReflectionOperator(builder()).getValue("children");
    }
}
