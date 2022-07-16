package com.legyver.fenxlib.api.icons.application;

import com.legyver.fenxlib.api.alert.ApplicationAlertIcons;
import com.legyver.fenxlib.api.alert.Level;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IconAliasMapTest {

    @Test
    public void defaultApplicationIcons() {
        IconAliasMap iconAliasMap = IconAliasMap.withDefaultAlertIcons().build();
        {
            String iconName = iconAliasMap.lookupIconName(Level.INFO);
            assertThat(iconName).isEqualTo(ApplicationAlertIcons.ICON_INFO.getIconName());
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.WARNING);
            assertThat(iconName).isEqualTo(ApplicationAlertIcons.ICON_WARNING.getIconName());
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.ERROR);
            assertThat(iconName).isEqualTo(ApplicationAlertIcons.ICON_NOTIFICATION.getIconName());
        }
    }

    @Test
    public void overrideDefaultApplicationIcons() {
        IconAliasMap iconAliasMap = IconAliasMap.withDefaultAlertIcons()
                .given(Level.INFO).thenUseIcon("test-info")
                .given(Level.WARNING).thenUseIcon("test-warning")
                .given(Level.ERROR).thenUseIcon("test-error")
                .build();
        {
            String iconName = iconAliasMap.lookupIconName(Level.INFO);
            assertThat(iconName).isEqualTo("test-info");
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.WARNING);
            assertThat(iconName).isEqualTo("test-warning");
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.ERROR);
            assertThat(iconName).isEqualTo("test-error");
        }
    }

    @Test
    public void predicateIcons() {
        IconAliasMap iconAliasMap = IconAliasMap
                .given(Level.INFO)
                    .when(o -> o instanceof Boolean && (Boolean) o)
                        .thenUseIcon("test-info-true")
                    .when(o -> o instanceof Boolean && !(Boolean) o)
                        .thenUseIcon("test-info-false")
                .given(Level.WARNING).thenUseIcon("test-warning")
                .given(Level.ERROR).thenUseIcon("test-error")
                .build();
        {
            String iconName = iconAliasMap.lookupIconName(Level.INFO, true);
            assertThat(iconName).isEqualTo("test-info-true");
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.INFO, false);
            assertThat(iconName).isEqualTo("test-info-false");
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.WARNING);
            assertThat(iconName).isEqualTo("test-warning");
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.ERROR);
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
                    .when(new IconAliasMap.NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return o == null;
                        }
                    })
                        .thenUseIcon("test-info-null")
                    .when(new IconAliasMap.NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is not null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return o != null;
                        }
                    })
                        .thenUseIcon("test-info-not-null")
                .build();

        {
            String iconName = iconAliasMap.lookupIconName(Level.INFO, null);
            assertThat(iconName).isEqualTo("test-info-null");
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.INFO, "not null");
            assertThat(iconName).isEqualTo("test-info-not-null");
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.ERROR);
            assertThat(iconName).isNull();
        }
    }

    @Test
    public void overriddenPredicateIconsUpdate() {
        IconAliasMap iconAliasMap = IconAliasMap
                .given(Level.INFO)
                    //only named predicates can be overridden
                    //this first one should never be seen because it will be overridden
                    .when(new IconAliasMap.NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return o == null;
                        }
                    })
                        .thenUseIcon("test-info-null")
                    .when(new IconAliasMap.NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is not null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return o != null;
                        }
                    })
                        .thenUseIcon("test-info-not-null")
                .build();

        //overrides
        IconAliasMap overrides = IconAliasMap
                .given(Level.INFO)
                //disable the value is null rule by changing the predicate to always return false
                    .when(new IconAliasMap.NamedPredicate() {
                        @Override
                        public String getName() {
                            return "value is null";
                        }

                        @Override
                        public boolean test(Object o) {
                            return false;
                        }
                    })
                        .thenUseIcon("never-see-this")
                //add in another formerly unspecified given
                .given(Level.ERROR).thenUseIcon("test-error")
                    .build();
        //merge in overrides
        iconAliasMap.merge(overrides);
        {
            String iconName = iconAliasMap.lookupIconName(Level.INFO, null);
            assertThat(iconName).isNull();
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.INFO, "not null");
            assertThat(iconName).isEqualTo("test-info-not-null");
        }
        {
            String iconName = iconAliasMap.lookupIconName(Level.ERROR);
            assertThat(iconName).isEqualTo("test-error");
        }
    }



}
