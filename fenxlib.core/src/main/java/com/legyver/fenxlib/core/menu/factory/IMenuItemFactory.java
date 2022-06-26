package com.legyver.fenxlib.core.menu.factory;

import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.core.controls.options.StyleableControlOptions;
import com.legyver.fenxlib.core.scene.controls.options.MenuItemOptions;
import javafx.scene.control.MenuItem;

/**
 * Type for factory that creates a menu item
 */
public interface IMenuItemFactory<T extends MenuItem, U extends StyleableControlOptions<T>> extends StyleableFactory<T, U> {

}
