package com.legyver.fenxlib.api.controls.builder;

import com.legyver.fenxlib.api.factory.adapters.ItemsAdapter;
import com.legyver.fenxlib.api.util.reflection.ReflectionOperator;

import java.util.List;

/**
 * Mixin for controls that support an items property.
 * You must have a field called 'items' in your *Options builder to use this mixin
 * @param <T> the type of the options
 */
public interface ItemsMixin<T extends BaseControlBuilder> extends OptionMixin<T> {

    /**
     * Specify items to be used.
     * @param items the items to be used
     * @return the builder using this mixin
     */
    default T items(List items) {
        new ReflectionOperator(builder()).setValue("items", items);
        return builder();
    }

    /**
     * Get the items to be used
     * @return the items
     */
    default List getItems() {
        return (List) new ReflectionOperator(builder()).getValue("items");
    }

    /**
     * Wrap the items in a null-safe adapter
     * @return an ItemsAdapter
     */
    default ItemsAdapter itemsAdapter() {
        return new ItemsAdapter(getItems());
    }
}
