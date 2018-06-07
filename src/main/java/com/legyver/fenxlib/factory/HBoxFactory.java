package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import com.legyver.fenxlib.locator.LocationContext;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class HBoxFactory implements NodeFactory<HBox> {
	private final NodeFactory[] nodeFactories;
	private final Pos alignment;

	public HBoxFactory(Pos alignment, NodeFactory... nodeFactories) {
		this.nodeFactories = nodeFactories;
		this.alignment = alignment;
	}

	public HBoxFactory(NodeFactory... nodeFactories) {
		this(Pos.CENTER_LEFT, nodeFactories);
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
		}
		return hbox;
	}

	@Override
	public HBox makeNode(LocationContext locationContext) throws CoreException {
		return makeHBox(locationContext);
	}

}
