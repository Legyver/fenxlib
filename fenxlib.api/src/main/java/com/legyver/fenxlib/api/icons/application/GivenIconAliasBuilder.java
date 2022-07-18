package com.legyver.fenxlib.api.icons.application;

import com.legyver.fenxlib.api.icons.options.IconOptions;

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
     * @param iconOptions the icon to use
     * @return the icon alias builder to allow specifying additional aliases or building the alias map
     */
    IconAliasBuilder thenUseIcon(IconOptions iconOptions);

    /**
     * Specify the icon to use regardless of any predicate.
     * @param builder the un-built icon options to use
     * @return the icon alias builder to allow specifying additional aliases or building the alias map
     */
    default IconAliasBuilder thenUseIcon(IconOptions.Builder builder) {
        return thenUseIcon(builder.build());
    }

    /**
     * Specify the icon to use regardless of any predicate.
     * @param family the family of the icon
     * @param iconName the name of the icon
     * @return the icon alias builder to allow specifying additional aliases or building the alias map
     */
    default IconAliasBuilder thenUseIcon(String family, String iconName) {
        return thenUseIcon(new IconOptions.Builder<>()
                .family(family)
                .icon(iconName)
        );
    }

}
