package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.scene.controls.options.SplitPaneOptions;
import com.legyver.fenxlib.core.util.LocationContextOperator;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Wrapper factory to embed the nested factories output into a SplitPane.
 */
public class SplitPaneFactory implements StyleableFactory<SplitPane, SplitPaneOptions> {

	@Override
	public SplitPane makeNode(LocationContext lc, SplitPaneOptions options) throws CoreException {
		LocationContext decorated = lc.decorateWith(options.getName());

		SplitPane splitPane;
		List<Region> children = options.getChildren();
		if (children != null) {
			splitPane = new SplitPane(children.toArray(new Region[children.size()]));
			for (int i = 0; i < children.size(); i++) {
				Region region = children.get(i);
				LocationContextOperator locationContextOperator = new LocationContextOperator(region);
				locationContextOperator.reRegister(decorated, "region_" + i);

				if (i == 0) {
					VBox.setVgrow(region, Priority.NEVER);
					//Constrain max size of top component:
					if (options.getSplitPercentage() != null) {
						region.maxHeightProperty().bind(splitPane.heightProperty().multiply(options.getSplitPercentage()));
					}
					region.minWidthProperty().bind(splitPane.maxWidthProperty());
				} else {
					VBox.setVgrow(region, Priority.ALWAYS);
				}
			}
		} else {
			splitPane = new SplitPane();
		}
		if (options.getOrientation() != null) {
			splitPane.setOrientation(options.getOrientation());
		}
		if (options.getSplitPercentage() != null) {
			splitPane.setDividerPositions(options.getSplitPercentage());
		}
		Fenxlib.register(decorated, splitPane);

		return splitPane;
	}

	@Override
	public SplitPaneOptions newOptions() {
		return new SplitPaneOptions();
	}

}
