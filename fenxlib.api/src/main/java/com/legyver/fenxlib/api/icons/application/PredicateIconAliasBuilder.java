package com.legyver.fenxlib.api.icons.application;

import java.util.function.Predicate;

/**
 * Builder for specifying a predicate attached to an icon alias determining when that alias should be applied
 */
public interface PredicateIconAliasBuilder extends IconAliasBuilder {
    /**
     * Specify a predicate attached to an icon alias determining when that alias should be applied
     * @param predicate the predicate
     * @return the predicate icon alias builder
     */
    PredicateIconAliasBuilder when(Predicate predicate);

    /**
     * Specify the icon to use when the predicate evaluates to true
     * @param icon the icon to use
     * @return this builder
     */
    PredicateIconAliasBuilder thenUseIcon(String icon);
}
