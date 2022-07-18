package com.legyver.fenxlib.api.icons.application;

import com.legyver.fenxlib.api.icons.options.IconOptions;

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
     * Specify a fallback icon options for if no other predicate is satisfied
     * @param iconOptions the fallback options
     * @return parent builder
     */
    IconAliasBuilder otherwise(IconOptions iconOptions);

    /**
     * Specify the icon to use when the predicate evaluates to true
     * @param iconOptions the icon to use
     * @return this builder
     */
    PredicateIconAliasBuilder thenUseIcon(IconOptions iconOptions);


    /**
     * Specify the icon to use regardless of any predicate.
     * @param builder the un-built icon options to use
     * @return the icon alias builder to allow specifying additional aliases or building the alias map
     */
    default PredicateIconAliasBuilder thenUseIcon(IconOptions.Builder builder) {
        return thenUseIcon(builder.build());
    }

    /**
     * Specify the icon to use regardless of any predicate.
     * @param family the family of the icon
     * @param iconName the name of the icon
     * @return the icon alias builder to allow specifying additional aliases or building the alias map
     */
    default PredicateIconAliasBuilder thenUseIcon(String family, String iconName) {
        return thenUseIcon(new IconOptions.Builder<>()
                .family(family)
                .icon(iconName)
        );
    }
}
