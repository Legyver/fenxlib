package com.legyver.fenxlib.core.factory;

import com.jfoenix.controls.JFXDialogLayout;
import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.factory.adapter.TitleFactoryAdapter;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

/**
 * &lt;JFXDialogLayout&gt;
 * &lt;heading&gt;
 * &lt;Label&gt;JFoenix Dialog&lt;/Label&gt;
 * &lt;/heading&gt;
 * &lt;body&gt;
 * &lt;Label&gt;Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
 * eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim
 * veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea
 * commodo consequat.
 * &lt;/Label&gt;
 * &lt;/body&gt;
 * &lt;actions&gt;
 * &lt;!--added in encapsulating PopupMenuItemFactory --&gt;
 * &lt;/actions&gt;
 * &lt;/JFXDialogLayout&gt;
 */
public class JFXDialogLayoutFactory extends AbstractWrappingFactory implements NodeFactory<JFXDialogLayout> {
	private ITitleFactory titleFactory;

	public JFXDialogLayoutFactory(ITitleFactory titleFactory, NodeFactory... nodeFactories) {
		super(nodeFactories);
		this.titleFactory = titleFactory;
	}

	public JFXDialogLayoutFactory(String title, NodeFactory...nodeFactories) {
		this(new TitleFactoryAdapter(new LabelFactory(title)), nodeFactories);
	}

	@Override
	public JFXDialogLayout makeNode(LocationContext locationContext) throws CoreException {
		JFXDialogLayout layout = new JFXDialogLayout();
		layout.setHeading((Node) titleFactory.makeNode(locationContext));
		VBox vbox = new VBox();
		layout.getBody().add(vbox);

		addChildren(vbox.getChildren(), locationContext);
		return layout;
	}

}
