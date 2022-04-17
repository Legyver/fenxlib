package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;

import java.util.List;

import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 * Factory to create wrap the output of the nested factories in an HBox
 */
public class HBoxFactory extends AbstractWrappingFactory implements StyleableFactory<HBox> {
	private final Pos alignment;
	private final boolean bindWidth;

	/**
	 * Construct a factory to wrap the output of the nested factories in an HBox
	 * @param alignment alignment to apply
	 * @param bindWidth if the width of the HBox to the width of the first node
	 * @param nodeFactories factories to create the nodes in the HBox
	 */
	public HBoxFactory(Pos alignment, boolean bindWidth, NodeFactory... nodeFactories) {
		super(nodeFactories);
		this.alignment = alignment;
		this.bindWidth = bindWidth;
	}

	/**
	 * Construct a factory to wrap the output of the nested factories in an HBox.
	 * Note: Uses {@link Pos#CENTER_LEFT} by default
	 * @param nodeFactories factories to create the nodes in the HBox
	 */
	public HBoxFactory(NodeFactory... nodeFactories) {
		this(Pos.CENTER_LEFT, false, nodeFactories);
	}

	/**
	 * Make the HBox
	 * @param locationContext the location context to use when registering components
	 * @return the HBox
	 * @throws CoreException if any error occurs creating child components
	 */
	public HBox makeHBox(LocationContext locationContext) throws CoreException {
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		HBox.setHgrow(hbox, Priority.SOMETIMES);
		hbox.setAlignment(alignment);
		if (styleableFactories != null) {
			addChildren(hbox.getChildren(), locationContext);
			List nodes = hbox.getChildren();

			if (bindWidth && !nodes.isEmpty()) {
				Object node = nodes.get(0);
				if (node instanceof Region) {
					hbox.minWidthProperty().bind(((Region) node).widthProperty());
				}

			}
		}
		return hbox;
	}

	@Override
	public HBox makeNode(LocationContext locationContext) throws CoreException {
		return makeHBox(locationContext);
	}

}
