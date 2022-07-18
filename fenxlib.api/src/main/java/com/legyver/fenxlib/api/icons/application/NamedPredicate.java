package com.legyver.fenxlib.api.icons.application;

import java.util.function.Predicate;

/**
 * Predicate that can be named.
 * This allows the particular predicate to be overridden by merging in an additional alias map with a different predicate of the same name
 * @param <T> the type of the argument in the test method
 */
public interface NamedPredicate<T> extends Predicate<T> {
    /**
     * Get the predicate name.
     * @return the predicate name
     */
    String getName();
}
