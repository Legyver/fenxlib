package com.legyver.fenxlib.core.menu.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.ControlsFactory;
import com.legyver.fenxlib.core.controls.factory.PopupFactory;
import com.legyver.fenxlib.core.controls.popup.Popup;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.query.ComponentQuery;
import com.legyver.fenxlib.core.util.map.MapBuilder;
import javafx.css.Styleable;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.legyver.fenxlib.core.controls.factory.SceneFactory.FENXLIB_MAIN_APPLICATION_PANE;
import static com.legyver.fenxlib.core.controls.factory.SceneFactory.FENXLIB_POPUP_PANE;


/**
 * Factory to launch a popup window when a menu item is clicked
 */
public class PopupMenuItemFactory implements IMenuItemFactory {
	private static final Logger logger = LogManager.getLogger(PopupMenuItemFactory.class);
	/**
	 * Constructor param to pass the Popup MenuItem name via a map
	 */
	public static final String PARAM_NAME = "name";
	/**
	 * Constructor param to pass the Popup content via a map
	 */
	public static final String PARAM_CONTENT = "content";
	/**
	 * Constructor param to pass the Popup buttons via a map
	 */
	public static final String PARAM_BUTTONS = "buttons";

	private final String name;
	private final Node content;
	private final Node buttons;

	/**
	 * Construct a factory to create MenuItems that launch a popup window
	 * @param name the name of the menu option
	 * @param content the content to display in the popup
	 * @param buttons the buttons to display on the popup
	 */
	public PopupMenuItemFactory(String name, Node content, Node buttons) {
		this.name = name;
		this.content = content;
		this.buttons = buttons;
	}

	@Override
	public Styleable makeNode(LocationContext locationContext) throws CoreException {
		MenuItem menuItem = new MenuItem(name);
		menuItem.setOnAction(e -> {
			try {
				Popup popup = ControlsFactory.make(Popup.class, locationContext, MapBuilder
						.of(PopupFactory.PARAM_CONTENT, content)
						.with(PopupFactory.PARAM_BUTTONS, buttons)
						.build());
				StackPane stackPane = (StackPane) new ComponentQuery.QueryBuilder()
						.inRegion(FENXLIB_MAIN_APPLICATION_PANE)
						.named(FENXLIB_POPUP_PANE)
						.execute()
						.get();
				stackPane.getChildren().add(popup);
			} catch (CoreException ex) {
				logger.error("Error executing action", ex);
			}
		});
		return menuItem;
	}
}
