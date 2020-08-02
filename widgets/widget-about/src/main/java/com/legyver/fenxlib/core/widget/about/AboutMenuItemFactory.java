package com.legyver.fenxlib.core.widget.about;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.JFXDialogLayoutFactory;
import com.legyver.fenxlib.core.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.factory.menu.MenuItemOptions;
import com.legyver.fenxlib.core.factory.menu.PopupMenuItemFactory;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

import java.util.function.Supplier;

public class AboutMenuItemFactory implements IMenuItemFactory<MenuItemOptions> {

	private final String name;
	private final Supplier<StackPane> dialogContainerSupplier;
	private final AboutPageOptions aboutPageOptions;

	public AboutMenuItemFactory(String name, Supplier<StackPane> dialogContainerSupplier, AboutPageOptions aboutPageOptions) {
		this.name = name;
		this.dialogContainerSupplier = dialogContainerSupplier;
		this.aboutPageOptions = aboutPageOptions;
	}

	@Override
	public MenuItem makeItem() throws CoreException {
		PopupMenuItemFactory popupMenuItemFactory = new PopupMenuItemFactory(name, dialogContainerSupplier,
				new JFXDialogLayoutFactory(aboutPageOptions.getTitle(), new AboutPageFactory(aboutPageOptions)));
		return popupMenuItemFactory.makeItem();
	}
}
