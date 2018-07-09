package com.legyver.fenxlib.factory;

import com.jfoenix.controls.JFXDialogLayout;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
		VBox vbox = new VBox();
		layout.getBody().add(vbox);

		addChildren(vbox.getChildren(), locationContext);
		return layout;
	}

}
