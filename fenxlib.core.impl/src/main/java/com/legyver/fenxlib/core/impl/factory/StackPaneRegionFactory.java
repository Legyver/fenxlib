package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.factory.options.RegionInitializationOptions;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

/**
 * Wrapping factory to embed the nested factories output in a StackPane
 */
public class StackPaneRegionFactory extends AbstractWrappingFactory implements RegionFactory<StackPane, RegionInitializationOptions> {
	private final boolean shouldRegister;

	public StackPaneRegionFactory(boolean shouldRegister, NodeFactory... nodeFactories) {
		super(nodeFactories);
		this.shouldRegister = shouldRegister;
	}

	@Override
	public StackPane makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
		StackPane content = new StackPane();
		if (shouldRegister) {
			ApplicationContext.getComponentRegistry().register(regionInitOptions.getLocationContext(), content);
		}
		addChildren(content.getChildren(), regionInitOptions.getLocationContext());
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
