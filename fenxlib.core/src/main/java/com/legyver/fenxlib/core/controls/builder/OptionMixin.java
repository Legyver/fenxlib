package com.legyver.fenxlib.core.controls.builder;

/**
 * Generic mixin
 * @param <T> the type of the builder this is mixed-in to
 */
public interface OptionMixin<T extends BaseControlBuilder> {
    /**
     * Get a reference to the builder that this is being mixed-in to
     * @return the builder
     */
    T builder();
}
