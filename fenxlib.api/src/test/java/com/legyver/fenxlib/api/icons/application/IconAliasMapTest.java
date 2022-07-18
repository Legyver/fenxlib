package com.legyver.fenxlib.api.icons.application;

import com.legyver.fenxlib.api.alert.ApplicationAlertIcons;
import com.legyver.fenxlib.api.alert.Level;
import com.legyver.fenxlib.api.icons.options.IconOptions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IconAliasMapTest {

    @Test
    public void defaultApplicationIcons() {
        IconAliasMap iconAliasMap = IconAliasMap.withDefaultAlertIcons().build();
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.INFO).getIcon();
            assertThat(iconName).isEqualTo(ApplicationAlertIcons.ICON_INFO.getIconName());
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.WARNING).getIcon();
            assertThat(iconName).isEqualTo(ApplicationAlertIcons.ICON_WARNING.getIconName());
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.ERROR).getIcon();
            assertThat(iconName).isEqualTo(ApplicationAlertIcons.ICON_NOTIFICATION.getIconName());
        }
    }

    @Test
    public void overrideDefaultApplicationIcons() {
        IconAliasMap iconAliasMap = IconAliasMap.withDefaultAlertIcons()
                .given(Level.INFO).thenUseIcon("test", "test-info")
                .given(Level.WARNING).thenUseIcon("test", "test-warning")
                .given(Level.ERROR).thenUseIcon("test", "test-error")
                .build();
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.INFO).getIcon();
            assertThat(iconName).isEqualTo("test-info");
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.WARNING).getIcon();
            assertThat(iconName).isEqualTo("test-warning");
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.ERROR).getIcon();
            assertThat(iconName).isEqualTo("test-error");
        }
    }

    @Test
    public void predicateIcons() {
        IconAliasMap iconAliasMap = IconAliasMap
                .given(Level.INFO)
                    .when(o -> o instanceof Boolean && (Boolean) o)
                        .thenUseIcon("test", "test-info-true")
                    .when(o -> o instanceof Boolean && !(Boolean) o)
                        .thenUseIcon("test", "test-info-false")
                .given(Level.WARNING).thenUseIcon("test", "test-warning")
                .given(Level.ERROR).thenUseIcon("test", "test-error")
                .build();
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.INFO, true).getIcon();
            assertThat(iconName).isEqualTo("test-info-true");
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.INFO, false).getIcon();
            assertThat(iconName).isEqualTo("test-info-false");
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.WARNING).getIcon();
            assertThat(iconName).isEqualTo("test-warning");
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.ERROR).getIcon();
            assertThat(iconName).isEqualTo("test-error");
        }
    }

    @Test
    public void overriddenPredicateIconsOriginal() {
        //we need to test these in two separate test cases because overrides need to be applied before first evaluation.
        IconAliasMap iconAliasMap = IconAliasMap
                .given(Level.INFO)
                    //only named predicates can be overridden
                    //this first one should never be seen because it will be overridden
                    .when(new NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return o == null;
                        }
                    })
                        .thenUseIcon("test", "test-info-null")
                    .when(new NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is not null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return o != null;
                        }
                    })
                        .thenUseIcon("test", "test-info-not-null")
                .build();

        {
            String iconName = iconAliasMap.lookupIconOptions(Level.INFO, null).getIcon();
            assertThat(iconName).isEqualTo("test-info-null");
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.INFO, "not null").getIcon();
            assertThat(iconName).isEqualTo("test-info-not-null");
        }
        {
            IconOptions iconOptions = iconAliasMap.lookupIconOptions(Level.ERROR);
            assertThat(iconOptions).isNull();
        }
    }

    @Test
    public void overriddenPredicateIconsUpdate() {
        IconAliasMap iconAliasMap = IconAliasMap
                .given(Level.INFO)
                    //only named predicates can be overridden
                    //this first one should never be seen because it will be overridden
                    .when(new NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return o == null;
                        }
                    })
                        .thenUseIcon("test", "test-info-null")
                    .when(new NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is not null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return o != null;
                        }
                    })
                        .thenUseIcon("test", "test-info-not-null")
                .build();

        //overrides
        IconAliasMap overrides = IconAliasMap
                .given(Level.INFO)
                //disable the value is null rule by changing the predicate to always return false
                    .when(new NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return false;
                        }
                    })
                        .thenUseIcon("test", "never-see-this")
                //add in another formerly unspecified given
                .given(Level.ERROR).thenUseIcon("test", "test-error")
                    .build();
        //merge in overrides
        iconAliasMap.merge(overrides);
        {
            IconOptions iconOptions = iconAliasMap.lookupIconOptions(Level.INFO, null);
            assertThat(iconOptions).isNull();
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.INFO, "not null").getIcon();
            assertThat(iconName).isEqualTo("test-info-not-null");
        }
        {
            String iconName = iconAliasMap.lookupIconOptions(Level.ERROR).getIcon();
            assertThat(iconName).isEqualTo("test-error");
        }
    }



}
