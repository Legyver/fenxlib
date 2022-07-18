package com.legyver.fenxlib.api.icons.application;

import com.legyver.fenxlib.api.alert.ApplicationAlertIcons;
import com.legyver.fenxlib.api.alert.Level;
import com.legyver.fenxlib.api.icons.options.IconOptions;

import java.util.*;
import java.util.function.Predicate;

/**
 * Map of icon aliases to use for a specific application event/control
 */
public class IconAliasMap {
    private final Map<ApplicationIconAffiliated, TypedIconMap> allAliasMap = new HashMap<>();

    private IconAliasMap() {
        //use builder
    }

    /**
     * Look up the icon for the affiliated control/event
     * @param applicationIconAffiliated the control/event to look up the icon for
     * @return the icon name
     */
    public IconOptions lookupIconOptions(ApplicationIconAffiliated applicationIconAffiliated) {
        return lookupIconOptions(applicationIconAffiliated, null);
    }

    /**
     * Look up the icon for the affiliated control/event
     * @param applicationIconAffiliated  the control/event to look up the icon for
     * @param o an optional parameter to be assessed by the predicate in differentiating among aliases
     * @return the icon name
     */
    public IconOptions lookupIconOptions(ApplicationIconAffiliated applicationIconAffiliated, Object o) {
        TypedIconMap typedIconMap = allAliasMap.get(applicationIconAffiliated);
        if (typedIconMap == null) {
            return null;
        }
        return typedIconMap.lookupIconOptions(o);
    }

    /**
     * Entry point to constructing an alias map
     * @param applicationIconAffiliated the application event/control to alias
     * @return the given builder
     */
    public static GivenIconAliasBuilder given(ApplicationIconAffiliated applicationIconAffiliated) {
        GivenBuilder givenBuilder = new GivenBuilder(new IconAliasMap(), applicationIconAffiliated);
        return givenBuilder;
    }

    /**
     * Entry point to constructing an alias map with all the default icons for alerts
     * @return the icon alias builder
     */
    public static IconAliasBuilder withDefaultAlertIcons() {
        IconAliasMap iconAliasMap = new IconAliasMap();
        return new GivenBuilder(iconAliasMap, Level.INFO)
                    .thenUseIcon(new IconOptions.Builder<>()
                            .icon(ApplicationAlertIcons.ICON_INFO.getIconName())
                            .family("icomoon")
                            .iconSize(18)
                            .iconColorString("#2eb1ec")
                            .build())
                .given(Level.WARNING)
                    .thenUseIcon(new IconOptions.Builder<>()
                        .icon(ApplicationAlertIcons.ICON_WARNING.getIconName())
                        .family("icomoon")
                        .iconSize(18)
                        .iconColorString("#ecb02e")
                        .build())
                .given(Level.ERROR)
                .thenUseIcon(new IconOptions.Builder<>()
                        .icon(ApplicationAlertIcons.ICON_NOTIFICATION.getIconName())
                        .family("icomoon")
                        .iconSize(18)
                        .iconColorString("#ec2e3e")
                        .build());
    }

    /**
     * Merge in custom/overridden aliases
     * @param iconAliasMap the alias map to merge in
     */
    public void merge(IconAliasMap iconAliasMap) {
        for (Map.Entry<ApplicationIconAffiliated, TypedIconMap> entry : iconAliasMap.allAliasMap.entrySet()) {
            this.allAliasMap.merge(entry.getKey(), entry.getValue(), TypedIconMap::merge);
        }
    }

    private static class TypedIconMap {
        private IconOptions iconOptions;
        private final Map<Predicate, IconOptions> aliasMap = new HashMap<>();
        private final Map<Class, Map<Predicate, IconOptions>> typedMap = new HashMap<>();

        private TypedIconMap(IconOptions iconOptions) {
            this.iconOptions = iconOptions;
        }

        private IconOptions lookupIconOptions(Object o) {
            if (o != null) {
                Map<Predicate, IconOptions> typedAliasMap = typedMap.computeIfAbsent(o.getClass(), x -> new HashMap<>());
                if (typedAliasMap.isEmpty()) {
                    for (Iterator<Map.Entry<Predicate, IconOptions>> it = aliasMap.entrySet().iterator(); it.hasNext();) {
                        Map.Entry<Predicate, IconOptions> entry = it.next();
                        Predicate predicate = entry.getKey();
                        if (predicate instanceof HandlesPredicate) {
                            if (((HandlesPredicate<?>) predicate).handles(o.getClass())) {
                                typedAliasMap.put(predicate, entry.getValue());
                                it.remove();
                            }
                        } else {
                            try {
                                predicate.test(o);
                                typedAliasMap.put(predicate, entry.getValue());
                                it.remove();
                            } catch (ClassCastException classCastException) {
                                //ok
                            }
                        }
                    }
                }
                for (Map.Entry<Predicate, IconOptions> entry: typedAliasMap.entrySet()) {
                    Predicate predicate = entry.getKey();
                    if (predicate.test(o)) {
                        return entry.getValue();
                    }
                }
            } else {
                for (Map.Entry<Predicate, IconOptions> entry: aliasMap.entrySet()) {
                    Predicate predicate = entry.getKey();
                    if (predicate.test(o)) {
                        return entry.getValue();
                    }
                }
            }

            //if no predicate has matched, use default value
            if (iconOptions != null) {
                return iconOptions;
            }

            return null;
        }

        private static TypedIconMap merge(TypedIconMap typedIconMap, TypedIconMap typedIconMap2) {
            if (typedIconMap.iconOptions != null && typedIconMap2.iconOptions != null) {
                return typedIconMap2;
            }

            //we can only override named predicates as it is too expensive to compute if two predicates are equivalent
            Map<String, Predicate> existingNamedPredicates = new HashMap<>();

            if (!typedIconMap.aliasMap.isEmpty()) {
                accumulateNamedPredicates(existingNamedPredicates, typedIconMap.aliasMap);
            }

            for (Map.Entry<Predicate, IconOptions> entry : typedIconMap2.aliasMap.entrySet()) {
                Predicate predicate = entry.getKey();
                if (predicate instanceof NamedPredicate) {
                    String name = ((NamedPredicate) predicate).getName();
                    Predicate existingPredicate = existingNamedPredicates.get(name);
                    if (existingPredicate != null) {
                        typedIconMap.aliasMap.remove(existingPredicate);
                    }
                    typedIconMap.aliasMap.put(predicate, entry.getValue());
                }
            }
            //done manual merging, return the original
            return typedIconMap;
        }

        private static void accumulateNamedPredicates(Map<String, Predicate> existingNamedPredicates, Map<Predicate, IconOptions> useMap) {
            for (Predicate predicate : useMap.keySet()) {
                if (predicate instanceof NamedPredicate) {
                    String name = ((NamedPredicate) predicate).getName();
                    existingNamedPredicates.put(name, predicate);
                }
            }
        }
    }

    private static class GivenBuilder implements GivenIconAliasBuilder {
        private final IconAliasMap iconAliasMap;
        private final ApplicationIconAffiliated applicationIconAffiliated;

        private List<PredicateBuilder> predicateBuilders = new ArrayList<>();
        private IconOptions iconOptions;

        private GivenBuilder(IconAliasMap iconAliasMap, ApplicationIconAffiliated applicationIconAffiliated) {
            this.iconAliasMap = iconAliasMap;
            this.applicationIconAffiliated = applicationIconAffiliated;
        }

        public IconAliasMap build() {
            TypedIconMap typedIconMap =  new TypedIconMap(iconOptions);
            for (PredicateBuilder predicateBuilder : predicateBuilders) {
                TypedIconMap builtIconMap = predicateBuilder.buildMap();
                for (Map.Entry<Predicate, IconOptions> entry : builtIconMap.aliasMap.entrySet()) {
                    typedIconMap.aliasMap.merge(entry.getKey(), entry.getValue(), (s1, s2) -> s1 != null ? s1 : s2);
                }
                if (builtIconMap.iconOptions != null) {
                    typedIconMap.iconOptions = builtIconMap.iconOptions;
                }
            }
            iconAliasMap.allAliasMap.put(applicationIconAffiliated, typedIconMap);
            return iconAliasMap;
        }

        @Override
        public GivenIconAliasBuilder given(ApplicationIconAffiliated iconType) {
            build();
            return new GivenBuilder(iconAliasMap, iconType);
        }

        @Override
        public PredicateIconAliasBuilder when(Predicate predicate) {
            PredicateBuilder predicateBuilder = new PredicateBuilder(this);
            predicateBuilders.add(predicateBuilder);
            return predicateBuilder.when(predicate);
        }

        @Override
        public IconAliasBuilder thenUseIcon(IconOptions iconOptions) {
            this.iconOptions = iconOptions;
            return this;
        }
    }

    private static class PredicateBuilder implements PredicateIconAliasBuilder {
        private final GivenBuilder givenBuilder;
        private List<WhenBuilder> whenBuilders = new ArrayList<>();
        private IconOptions iconOptions;

        private PredicateBuilder(GivenBuilder givenBuilder) {
            this.givenBuilder = givenBuilder;
        }

        @Override
        public IconAliasMap build() {
            return givenBuilder.build();
        }

        @Override
        public GivenIconAliasBuilder given(ApplicationIconAffiliated iconType) {
            return new GivenBuilder(givenBuilder.iconAliasMap, iconType);
        }

        private TypedIconMap buildMap() {
            TypedIconMap typedIconMap = new TypedIconMap(iconOptions);
            for (WhenBuilder whenBuilder : whenBuilders) {
                if (whenBuilder.predicate != null && whenBuilder.thenBuilder != null) {
                    typedIconMap.aliasMap.put(whenBuilder.predicate, whenBuilder.thenBuilder.iconOptions);
                }
            }
            return typedIconMap;
        }

        @Override
        public PredicateIconAliasBuilder when(Predicate predicate) {
            WhenBuilder whenBuilder = new WhenBuilder(givenBuilder, this, predicate);
            whenBuilders.add(whenBuilder);
            return whenBuilder;
        }

        @Override
        public IconAliasBuilder otherwise(IconOptions iconOptions) {
            this.iconOptions = iconOptions;
            return givenBuilder;
        }

        @Override
        public PredicateIconAliasBuilder thenUseIcon(IconOptions iconOptions) {
            this.iconOptions = iconOptions;
            return this;
        }
    }

    private static class WhenBuilder implements PredicateIconAliasBuilder {
        private final GivenBuilder givenBuilder;
        private final PredicateBuilder predicateBuilder;
        private Predicate predicate;
        private ThenBuilder thenBuilder;

        private WhenBuilder(GivenBuilder givenBuilder, PredicateBuilder predicateBuilder, Predicate predicate) {
            this.givenBuilder = givenBuilder;
            this.predicateBuilder = predicateBuilder;
            this.predicate = predicate;
        }

        public PredicateIconAliasBuilder thenUseIcon(IconOptions iconOptions) {
            thenBuilder = new ThenBuilder(givenBuilder, predicateBuilder, iconOptions);
            return this;
        }

        @Override
        public IconAliasMap build() {
            return givenBuilder.build();
        }

        @Override
        public GivenIconAliasBuilder given(ApplicationIconAffiliated iconType) {
            givenBuilder.build();
            return new GivenBuilder(givenBuilder.iconAliasMap, iconType);
        }

        @Override
        public PredicateIconAliasBuilder when(Predicate predicate) {
            return givenBuilder.when(predicate);
        }

        @Override
        public IconAliasBuilder otherwise(IconOptions iconOptions) {
            return predicateBuilder.otherwise(iconOptions);
        }
    }

    private static class ThenBuilder implements IconAliasBuilder {
        private final GivenBuilder givenBuilder;
        private final PredicateBuilder builder;
        private IconOptions iconOptions;

        private ThenBuilder(GivenBuilder givenBuilder, PredicateBuilder builder, IconOptions iconOptions) {
            this.givenBuilder = givenBuilder;
            this.builder = builder;
            this.iconOptions = iconOptions;
        }

        @Override
        public IconAliasMap build() {
            return builder.build();
        }

        @Override
        public GivenBuilder given(ApplicationIconAffiliated iconType) {
            return new GivenBuilder(givenBuilder.iconAliasMap, iconType);
        }
    }
}
