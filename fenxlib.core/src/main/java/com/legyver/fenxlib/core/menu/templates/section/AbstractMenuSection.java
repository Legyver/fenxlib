package com.legyver.fenxlib.core.menu.templates.section;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.menu.options.AbstractMenuItemProducer;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class for a menu section
 */
public class AbstractMenuSection {
    private final List<AbstractMenuItemProducer> menuItemProducers;

    /**
     * Construct a section in a menu with the specified options for child items
     * @param menuItemProducers producers describing the menu items to be included in this menu section
     */
    public AbstractMenuSection(List<AbstractMenuItemProducer> menuItemProducers) {
        this.menuItemProducers = menuItemProducers;
    }

    /**
     * Construct a section in a menu with no items.
     * This may be handy for things where the menu section is initially empty such as File > Open Recent
     */
    public AbstractMenuSection() {
        this(new ArrayList<>());
    }

    /**
     * Construct the menu items for a section in a menu
     * @param locationContext the location context for the menu
     * @return an ordered list of menu items to include in the section
     * @throws CoreException if there is an error producing the menu item
     */
    public List<MenuItem> makeMenuItems(LocationContext locationContext) throws CoreException {
        List<MenuItem> result = new ArrayList<>(menuItemProducers.size());
        for (AbstractMenuItemProducer menuItemProducer : menuItemProducers) {
            MenuItem menuItem = menuItemProducer.makeMenuItem(locationContext);
            result.add(menuItem);
        }
        return result;
    }

}
