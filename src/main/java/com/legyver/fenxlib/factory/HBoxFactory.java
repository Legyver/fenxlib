package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;

import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class HBoxFactory extends AbstractWrappingFactory implements NodeFactory<HBox> {
	private final Pos alignment;
	private final boolean bindWidth;

	public HBoxFactory(Pos alignment, boolean bindWidth, NodeFactory... nodeFactories) {
		super(nodeFactories);
		this.alignment = alignment;
		this.bindWidth = bindWidth;
	}

	public HBoxFactory(NodeFactory... nodeFactories) {
		this(Pos.CENTER_LEFT, false, nodeFactories);
	}

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
