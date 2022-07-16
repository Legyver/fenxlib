package com.legyver.fenxlib.api.icons.application;

import java.util.function.Predicate;

/**
 * Builder for specifying the conditions upon a particular alias should be used
 */
public interface GivenIconAliasBuilder extends IconAliasBuilder {
    /**
     * Specify the conditions on when an icon should be used
     * @param predicate the predicate a true evaluation of will result in the icon alias being selected
     * @return the icon alias predicate builder
     */
    PredicateIconAliasBuilder when(Predicate predicate);

    /**
     * Specify the icon to use regardless of any predicate.
     * @param icon the icon to use
     * @return the icon alias builder to allow specifying additional aliases or building the alias map
     */
    IconAliasBuilder thenUseIcon(String icon);

}
