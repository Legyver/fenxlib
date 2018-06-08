package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.RegionInitializationOptions;
import java.util.stream.Stream;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class PaneRegionFactory implements RegionFactory<Pane, RegionInitializationOptions> {

	private final NodeFactory[] nodeFactories;

	public PaneRegionFactory(NodeFactory... nodeFactories) {
		this.nodeFactories = nodeFactories;
	}

	@Override
	public Pane makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
		Pane content = new Pane();
		if (nodeFactories != null) {
			unwrap(() -> Stream.of(nodeFactories)
					.map(f -> wrap(() -> f.makeNode(regionInitOptions.getLocationContext())))
					.forEach(n -> content.getChildren().add(n)));
		}
		return content;
	}

}
