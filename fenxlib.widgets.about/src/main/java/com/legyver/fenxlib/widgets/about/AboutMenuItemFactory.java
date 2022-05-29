package com.legyver.fenxlib.widgets.about;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.ControlsFactory;
import com.legyver.fenxlib.core.controls.factory.SceneFactory;
import com.legyver.fenxlib.core.controls.popup.LaunchPopupAction;
import com.legyver.fenxlib.core.layout.Target;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.menu.factory.IMenuItemFactory;
import com.legyver.fenxlib.core.menu.factory.MenuItemFactory;
import com.legyver.fenxlib.core.util.map.MapBuilder;
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
	public MenuItem makeNode(LocationContext locationContext) throws CoreException {
		AboutPage aboutPage = new AboutPageFactory(aboutPageOptions).makeNode(locationContext);
		//place centered
		Target target = new Target.Builder()
				.centered()
				.build();
		//over main application screen
		ComponentQuery mainApplicationScreenQuery = new ComponentQuery.QueryBuilder().inRegion(SceneFactory.FENXLIB_MAIN_APPLICATION_PANE).only();

		MenuItem menuItem = ControlsFactory.make(MenuItem.class, locationContext, MapBuilder
				.of(MenuItemFactory.PARAM_NAME, name)
				.with(MenuItemFactory.PARAM_ACTION, new LaunchPopupAction(aboutPage, mainApplicationScreenQuery, target)).build());
		return menuItem;
	}
}
