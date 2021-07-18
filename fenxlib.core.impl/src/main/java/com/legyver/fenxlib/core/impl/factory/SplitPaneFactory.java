package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import java.util.List;

import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Wrapper factory to embed the nested factories output into a SplitPane.
 */
public class SplitPaneFactory extends AbstractWrappingFactory<Region> implements NodeFactory<SplitPane> {

	private final double topPercentage;

	/**
	 * Construct a factory to embed the content produced by the wrapped factories in a split pane.
	 * @param topPercentage the percentage of the split taken up by the top pane (as a percentage between 0.000 and 0.999)
	 * @param factories the factories producing the split pane content
	 */
	public SplitPaneFactory(double topPercentage, NodeFactory<? extends Region>... factories) {
		super(factories);
		this.topPercentage = topPercentage;
	}

	@Override
	public SplitPane makeNode(LocationContext lc) throws CoreException {
		List<Region> nodes = super.makeChildren(lc);

		SplitPane splitPane = new SplitPane(nodes.toArray(new Node[nodes.size()]));
		splitPane.setOrientation(Orientation.VERTICAL);
		splitPane.setDividerPositions(topPercentage);

		boolean first = true;
		for (Region region : nodes) {
			if (first){
				VBox.setVgrow(region, Priority.NEVER);
				//Constrain max size of top component:
				region.maxHeightProperty().bind(splitPane.heightProperty().multiply(topPercentage));
				region.minWidthProperty().bind(splitPane.maxWidthProperty());
			} else {
				VBox.setVgrow(region, Priority.ALWAYS);
			}
		}

		return splitPane;
	}

}
