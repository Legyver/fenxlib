package com.legyver.fenxlib.api.icons.application;

import java.util.function.Predicate;

/**
 * Predicate with an additional method that allows for testing type of argument it accepts in its test method
 * @param <T> the type of the argument in the test method
 */
public interface HandlesPredicate<T> extends Predicate<T> {
    /**
     * Return if the predicate can handle the type of the parameter without incurring a ClassCastException
     * @param klass the class of the potential object
     * @return true if the class type is supported
     */
    boolean handles(Class<?> klass);
}