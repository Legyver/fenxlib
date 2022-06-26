package com.legyver.fenxlib.core.controls.builder;

import com.legyver.fenxlib.core.factory.adapters.ItemsAdapter;
import com.legyver.fenxlib.core.util.reflection.ReflectionOperator;

import java.util.List;

public interface ItemsMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    default T items(List items) {
        new ReflectionOperator(builder()).setValue("items", items);
        return builder();
    }

    default List getItems() {
        return (List) new ReflectionOperator(builder()).getValue("items");
    }

    default ItemsAdapter itemsAdapter() {
        return new ItemsAdapter(getItems());
    }
}
