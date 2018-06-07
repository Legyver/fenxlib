package com.legyver.fenxlib.factory.menu;

import javafx.scene.control.MenuItem;

public interface IMenuItemFactory<T extends MenuItemOptions> {

	MenuItem makeItem();
}
