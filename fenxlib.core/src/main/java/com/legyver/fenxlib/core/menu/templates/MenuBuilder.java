package com.legyver.fenxlib.core.menu.templates;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.IComponentRegistry;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.regions.ApplicationRegions;
import com.legyver.fenxlib.api.scene.controls.options.MenuOptions;
import com.legyver.fenxlib.core.menu.section.MenuSection;
import com.legyver.fenxlib.core.util.PropertyMapExtractor;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

import java.util.*;

/**
 * Builder to create a menu
 */
public class MenuBuilder {
    private String name;

    private final List<MenuSectionOptions> sections = new ArrayList<>();

    /**
     * Build a menu the specified sections
     * @return the menu
     * @throws CoreException if there is an error producing a Menu or MenuItem control
     */
    public Menu build() throws CoreException {
        LocationContext menuContext = new DefaultLocationContext(ApplicationRegions.MENUS.getName());
        Menu menu = ControlsFactory.make(Menu.class, menuContext, new MenuOptions()
                .text(name));
        Map<Object, Object> menuProperties = new PropertyMapExtractor(menu).get();
        LocationContext locationContext = (LocationContext) menuProperties.get(IComponentRegistry.LOCATION_CONTEXT_PROPERTY);
        for (MenuSectionOptions menuSectionBuilder: sections) {
            List<MenuItem> menuItems = menuSectionBuilder.build(locationContext);
            menu.getItems().addAll(menuItems);
            menu.getItems().add(new SeparatorMenuItem());
        }
        return menu;
    }

    /**
     * Specify the name of the menu.  This can also be the property the name will be associated with.
     * @param name the name or i18n property for the menu
     * @return this builder
     */
    public MenuBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Specify a section of the menu.
     * @param menuables items to be included in the menu section.  These can be of type {@link MenuItem} or {@link MenuSection}
     * @return this builder
     */
    public MenuBuilder menuSection(Object... menuables) {
        sections.add(new MenuSectionOptions(menuables));
        return this;
    }

    /**
     * Options defining a section in a menu
     */
    private static class MenuSectionOptions {
        private final List<Object> items;

        /**
         * Construct options defining a section in a menu to host the specified items
         * @param items the items to include in the menu
         */
        MenuSectionOptions(Object... items) {
            this.items = items == null ? Collections.EMPTY_LIST : Arrays.asList(items);
        }

        private List<MenuItem> build(LocationContext locationContext) throws CoreException {
            List<MenuItem> menuItems = new ArrayList<>();
            for (Object item : items) {
                MenuItem menuItem = null;
                if (item instanceof MenuItem) {
                    menuItem = (MenuItem) item;
                } else if (item instanceof MenuSection) {
                    List<MenuItem> sectionItems = ((MenuSection) item).makeMenuItems(locationContext);
                    menuItems.addAll(sectionItems);
                }
                if (menuItem != null) {
                    menuItems.add(menuItem);
                }
            }
            return menuItems;
        }
    }

}
