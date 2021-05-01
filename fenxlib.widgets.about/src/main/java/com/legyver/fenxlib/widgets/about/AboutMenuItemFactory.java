package com.legyver.fenxlib.widgets.about;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.JFXDialogLayoutFactory;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.impl.factory.menu.PopupMenuItemFactory;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

import java.util.function.Supplier;

/**
 * Factory to create an 'About' MenuItem
 */
public class AboutMenuItemFactory implements IMenuItemFactory {

	private final String name;
	private final Supplier<StackPane> dialogContainerSupplier;
	private final AboutPageOptions aboutPageOptions;

	/**
	 * Construct an AboutMenuItem
	 * @param name the text for the menu item that launches the about page
	 * @param dialogContainerSupplier the callback that retrieves the StackPane to display the popup over
	 * @param aboutPageOptions options for rendering the AboutPage
	 */
	public AboutMenuItemFactory(String name, Supplier<StackPane> dialogContainerSupplier, AboutPageOptions aboutPageOptions) {
		this.name = name;
		this.dialogContainerSupplier = dialogContainerSupplier;
		this.aboutPageOptions = aboutPageOptions;
	}

	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		PopupMenuItemFactory popupMenuItemFactory = new PopupMenuItemFactory(name, dialogContainerSupplier,
				new JFXDialogLayoutFactory(aboutPageOptions.getTitle(), new AboutPageFactory(aboutPageOptions)));
		return popupMenuItemFactory.makeItem(locationContext);
	}
}
