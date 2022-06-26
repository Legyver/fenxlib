package com.legyver.fenxlib.core.menu.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.ControlsFactory;
import com.legyver.fenxlib.core.controls.factory.PopupFactory;
import com.legyver.fenxlib.core.controls.popup.Popup;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.scene.controls.options.MenuItemOptions;
import com.legyver.fenxlib.core.util.map.MapBuilder;
import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static com.legyver.fenxlib.core.controls.factory.SceneFactory.FENXLIB_MAIN_APPLICATION_PANE;
import static com.legyver.fenxlib.core.controls.factory.SceneFactory.FENXLIB_POPUP_PANE;


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
