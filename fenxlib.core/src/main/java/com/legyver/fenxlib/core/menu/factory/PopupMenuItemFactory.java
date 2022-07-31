package com.legyver.fenxlib.core.menu.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Factory to launch a popup window when a menu item is clicked
 */
public class PopupMenuItemFactory implements IMenuItemFactory<MenuItem, MenuItemOptions> {
	private static final Logger logger = LogManager.getLogger(PopupMenuItemFactory.class);

	@Override
	public MenuItem makeNode(LocationContext locationContext, MenuItemOptions options) throws CoreException {
		MenuItem menuItem = new MenuItem(options.getText());
		menuItem.setOnAction(e -> {
//			try {
//				Popup popup = ControlsFactory.make(Popup.class, locationContext, new PopupOptions()
//						.content(content)
//						.buttons(buttons)
//				);
//				StackPane stackPane = (StackPane) new ComponentQuery.QueryBuilder()
//						.inRegion(FENXLIB_MAIN_APPLICATION_PANE)
//						.named(FENXLIB_POPUP_PANE)
//						.execute()
//						.get();
//				stackPane.getChildren().add(popup);
//			} catch (CoreException ex) {
//				logger.error("Error executing action", ex);
//			}
		});
		return menuItem;
	}

	@Override
	public MenuItemOptions newOptions() {
		return new MenuItemOptions();
	}
}
