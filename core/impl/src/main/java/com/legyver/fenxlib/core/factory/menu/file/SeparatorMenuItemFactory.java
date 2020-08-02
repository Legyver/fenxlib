package com.legyver.fenxlib.core.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.menu.IMenuItemFactory;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class SeparatorMenuItemFactory implements IMenuItemFactory{
	
	@Override
	public MenuItem makeItem() throws CoreException {
		return new SeparatorMenuItem();
	}


}
