package com.legyver.fenxlib.core.menu.templates;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.IComponentRegistry;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.api.regions.ApplicationRegions;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.core.menu.section.IMenuable;
import com.legyver.fenxlib.core.menu.section.MenuSection;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import com.legyver.fenxlib.api.scene.controls.options.MenuOptions;
import com.legyver.fenxlib.core.util.PropertyMapExtractor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
     * Specify the name of the menu
     * @param name the name of the menu
     * @return this builder
     */
    public MenuBuilder name(String name) {
        this.name = name;
        return this;
    }

    /**
     * Specify a section of the menu
     * @param menuables items to be included in the menu section
     * @return this builder
     */
    public MenuBuilder menuSection(IMenuable... menuables) {
        sections.add(new MenuSectionOptions(menuables));
        return this;
    }

    /**
     * Options defining a section in a menu
     */
    public static class MenuSectionOptions {
        private final List<IMenuable> items;

        /**
         * Construct options defining a section in a menu to host the specified items
         * @param items the items to include in the menu
         */
        public MenuSectionOptions(IMenuable... items) {
            this.items = items == null ? Collections.EMPTY_LIST : Arrays.asList(items);
        }

        private List<MenuItem> build(LocationContext locationContext) throws CoreException {
            List<MenuItem> menuItems = new ArrayList<>();
            for (Object item : items) {
                MenuItem menuItem = null;
                if (item instanceof MenuItem) {
                    menuItem = (MenuItem) item;
                } else if (item instanceof MenuItemOption) {
                    menuItem = ((MenuItemOption) item).build(locationContext);
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

    /**
     * Options to describe a menu item
     */
    public static class MenuItemOption {
        private final String name;
        private final EventHandler<ActionEvent> eventHandler;

        /**
         * Construct an option for a menu item with a specified name and handler
         * @param name the name of the menu item
         * @param eventHandler what to do when the menu item is selected
         */
        public MenuItemOption(String name, EventHandler<ActionEvent> eventHandler) {
            this.name = name;
            this.eventHandler = eventHandler;
        }

        private MenuItem build(LocationContext locationContext) throws CoreException {
            LocationContextDecorator decoratedCtx = new LocationContextDecorator(locationContext);
            decoratedCtx.setName(name);
            return ControlsFactory.make(MenuItem.class, decoratedCtx, new MenuItemOptions()
                    .name(name)
                    .eventHandler(eventHandler)
            );
        }
    }
}
