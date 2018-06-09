package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.RegionInitializationOptions;
import java.util.stream.Stream;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class StackPaneRegionFactory implements RegionFactory<StackPane, RegionInitializationOptions> {

	private final NodeFactory[] nodeFactories;

	public StackPaneRegionFactory(NodeFactory... nodeFactories) {
		this.nodeFactories = nodeFactories;
	}

	@Override
	public StackPane makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
		StackPane content = new StackPane();
		if (nodeFactories != null) {
			unwrap(() -> Stream.of(nodeFactories)
					.map(f -> wrap(() -> f.makeNode(regionInitOptions.getLocationContext())))
					.forEach(n -> content.getChildren().add(n)));
		}
		ObservableList<Node> childs = content.getChildren();
		if (childs.size() == 1) {
			Node node = childs.get(0);

			if (node instanceof WebView) {
				WebView webView = (WebView) node;
				webView.minHeightProperty().bind(content.heightProperty());
				webView.prefWidthProperty().bind(content.widthProperty());
			}
		}
		return content;
	}

}
