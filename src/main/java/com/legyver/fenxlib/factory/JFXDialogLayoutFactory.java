package com.legyver.fenxlib.factory;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

/**
 * <JFXDialogLayout>
 * <heading>
 * <Label>JFoenix Dialog</Label>
 * </heading>
 * <body>
 * <Label>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
 * eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
 * veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
 * commodo consequat.
 * </Label>
 * </body>
 * <actions>
 * <!--added in encapsulating PopupMenuItemFactory -->
 * </actions>
 * </JFXDialogLayout>
 */
public class JFXDialogLayoutFactory extends AbstractWrappingFactory implements NodeFactory<JFXDialogLayout> {
	private final String title;

	public JFXDialogLayoutFactory(String title, NodeFactory... nodeFactories) {
		super(nodeFactories);
		this.title = title;
	}

	@Override
	public JFXDialogLayout makeNode(LocationContext locationContext) throws CoreException {
		JFXDialogLayout layout = new JFXDialogLayout();
		layout.setHeading(new Label(title));

		addChildren(layout.getBody(), locationContext);
		return layout;
	}

}
