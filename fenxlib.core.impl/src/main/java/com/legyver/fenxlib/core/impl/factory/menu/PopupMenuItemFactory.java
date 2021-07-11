package com.legyver.fenxlib.core.impl.factory.menu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.factory.JFXDialogLayoutFactory;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

/**
 * Factory to launch a popup window when a menu item is clicked
 */
public class PopupMenuItemFactory implements IMenuItemFactory {
	private static final Logger logger = LogManager.getLogger(PopupMenuItemFactory.class);

	private final String name;
	private final Supplier<StackPane> dialogContainerSupplier;
	private final JFXDialogLayoutFactory factory;

	/**
	 * Construct a factory to create MenuItems that launch a popup window
	 * @param name the name of the menu option
	 * @param dialogContainerSupplier supplies a reference to the container to position the popup over
	 * @param factory produces the layout for the popup
	 */
	public PopupMenuItemFactory(String name, Supplier<StackPane> dialogContainerSupplier, JFXDialogLayoutFactory factory) {
		this.name = name;
		this.dialogContainerSupplier = dialogContainerSupplier;
		this.factory = factory;
	}

	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		MenuItem menuItem = new MenuItem(name);

		JFXDialogLayout layout = factory.makeNode(null);
		JFXDialog dialog = new JFXDialog(null, layout, DialogTransition.RIGHT);

		JFXButton okButton = new JFXButton("OK");
		okButton.setOnMouseClicked((e) -> dialog.close());
		layout.setActions(okButton);

		menuItem.setOnAction(e -> {
			dialog.show(dialogContainerSupplier.get());//on-demand retrieval because it likely won't exist when this MenuItem is inited.
		});

		return menuItem;
	}

}
