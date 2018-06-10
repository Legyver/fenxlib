package com.legyver.fenxlib.factory.menu;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.JFXDialogLayoutFactory;
import java.util.function.Supplier;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.StackPane;

public class PopupMenuItemFactory implements IMenuItemFactory<MenuItemOptions> {
	private final String name;
	private final Supplier<StackPane> dialogContainerSupplier;
	private final JFXDialogLayoutFactory factory;

	public PopupMenuItemFactory(String name, Supplier<StackPane> dialogContainerSupplier, JFXDialogLayoutFactory factory) {
		this.name = name;
		this.dialogContainerSupplier = dialogContainerSupplier;
		this.factory = factory;
	}

	@Override
	public MenuItem makeItem() throws CoreException {
		MenuItem popup = new MenuItem(name);

		JFXDialogLayout layout = factory.makeNode(null);
		JFXDialog dialog = new JFXDialog(null, layout, DialogTransition.RIGHT);

		JFXButton okButton = new JFXButton("OK");
		okButton.setOnMouseClicked((e) -> dialog.close());
		layout.setActions(okButton);

		popup.setOnAction(e -> {
			dialog.show(dialogContainerSupplier.get());//on-demand retrieval because it likely won't exist when this MenuItem is inited.
		});

		return popup;
	}

}
