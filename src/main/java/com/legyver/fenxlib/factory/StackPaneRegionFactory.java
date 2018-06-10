package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.RegionInitializationOptions;
import com.legyver.fenxlib.util.GuiUtil;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebView;

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
			GuiUtil.getComponentRegistry().register(regionInitOptions.getLocationContext(), content);
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
