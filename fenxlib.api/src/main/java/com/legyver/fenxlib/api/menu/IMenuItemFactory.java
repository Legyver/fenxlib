package com.legyver.fenxlib.api.menu;

import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.controls.options.StyleableControlOptions;
import javafx.scene.control.MenuItem;

/**
 * Type for factory that creates a menu item
 */
public interface IMenuItemFactory<T extends MenuItem, U extends StyleableControlOptions<T>> extends StyleableFactory<T, U> {

}
