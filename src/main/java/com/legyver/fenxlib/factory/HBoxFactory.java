package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class HBoxFactory implements NodeFactory<HBox> {
	private final NodeFactory[] nodeFactories;
	private final Pos alignment;
	private final boolean bindWidth;

	public HBoxFactory(Pos alignment, boolean bindWidth, NodeFactory... nodeFactories) {
		this.nodeFactories = nodeFactories;
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
		if (nodeFactories != null) {
			List nodes = unwrap(() -> Stream.of(nodeFactories)
					.map(f -> wrap(() -> f.makeNode(locationContext)))
					.collect(Collectors.toList()));
			hbox.getChildren().addAll(nodes);
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
