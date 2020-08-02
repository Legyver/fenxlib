package com.legyver.fenxlib.core.factory.menu;

import com.legyver.core.exception.CoreException;
import javafx.scene.control.MenuItem;

public interface IMenuItemFactory<T extends MenuItemOptions> {

	MenuItem makeItem() throws CoreException;
}
