package com.legyver.fenxlib.core.menu.operator;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Operator handling menu item insertions.
 * Example: Insert an "Open Recent" menu after the "Open" menu item in the file menu
 *   new MenuOperator(fileMenu).insert(openRecentMenu).after("Open")
 *
 */
public class MenuOperator {
    private static final Logger logger = LogManager.getLogger(MenuOperator.class);

    private final Menu menu;

    /**
     * Construct a Menu Operator for a menu
     * @param menu the menu to operate on
     */
    public MenuOperator(Menu menu) {
        this.menu = menu;
    }

    /**
     * Insert a menu item in a menu
     * @param menuItem the menu item to insert
     * @return the order operator builder
     */
    public OrderOperator insert(MenuItem menuItem) {
        return new MenuOperator.OrderOperator(menuItem);
    }

    /**
     * Operator handling the order
     */
    public class OrderOperator {
        private final MenuItem menuItem;

        private OrderOperator(MenuItem menuItem) {
            this.menuItem = menuItem;
        }

        /**
         * Insert after the menu item named
         * @param name the name of the menu item
         */
        public void after(String name) {
            after(name, false);
        }

        /**
         * Insert after the menu item named
         * @param name the name of the menu item to insert this after
         * @param asNewSection true if you want to have a menu separator between this and the named menu item
         */
        public void after(String name, boolean asNewSection) {
            List<MenuItem> items = menu.getItems();
            int foundAt = -1;
            for (int i = 0; i < items.size(); i++) {
                MenuItem menuItem = items.get(i);
                String menuItemText = menuItem.getText();
                if (name.equals(menuItemText)) {
                    foundAt = i;
                    break;
                }
            }
            if (foundAt < 0) {
                logger.error("Unable to add {} after desired position since menu item [{}] was not found", menuItem.getText(), name);
            } else {
                foundAt++;//add after
                if (asNewSection) {
                    items.add(foundAt, new SeparatorMenuItem());
                    foundAt++;
                }
                items.add(foundAt, menuItem);
            }
        }

        /**
         * Insert before a named menu item
         * @param name the name of the menu item to insert this before
         */
        public void before(String name) {
            before(name, false);
        }

        /**
         * Insert before the named menu item
         * @param name the name of the menu item to insert this before
         * @param asNewSection true if you want to have a menu separator between this and the named menu item
         */
        public void before(String name, boolean asNewSection) {
            List<MenuItem> items = menu.getItems();
            int foundAt = -1;
            for (int i = 0; i < items.size(); i++) {
                MenuItem menuItem = items.get(i);
                String menuItemText = menuItem.getText();
                if (name.equals(menuItemText)) {
                    foundAt = i;
                    break;
                }
            }
            if (foundAt < 0) {
                logger.error("Unable to add {} before desired position since menu item [{}] was not found", menuItem.getText(), name);
            }
            if (foundAt > -1) {
                items.add(foundAt, menuItem);
                if (asNewSection) {
                    foundAt++;
                    items.add(foundAt, new SeparatorMenuItem());
                }
            }
        }

        /**
         * Add the menu item to the end of the menu.
         */
        public void atEnd() {
            atEnd(false);
        }

        /**
         * Add the menu item to the end of the menu.
         * @param asNewSection true if you want a menu divider positioned above the new menu item
         */
        public void atEnd(boolean asNewSection) {
            List<MenuItem> items = menu.getItems();
            if (asNewSection) {
                items.add(new SeparatorMenuItem());
            }
            items.add(menuItem);
        }

        /**
         * Add the menu item to the end of the menu.
         */
        public void atBeginning() {
            atBeginning(false);
        }

        /**
         * Add the menu item to the end of the menu.
         * @param asNewSection true if you want a menu separator positioned after the new menu item
         */
        public void atBeginning(boolean asNewSection) {
            List<MenuItem> items = menu.getItems();
            items.add(0, menuItem);
            if (asNewSection) {
                items.add(new SeparatorMenuItem());
            }
        }
    }

}
