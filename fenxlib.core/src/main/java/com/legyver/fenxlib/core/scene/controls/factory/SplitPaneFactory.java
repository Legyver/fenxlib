package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.controls.factory.AbstractWrappingFactory;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;

import java.util.List;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Wrapper factory to embed the nested factories output into a SplitPane.
 */
public class SplitPaneFactory extends AbstractWrappingFactory<Region> implements StyleableFactory<SplitPane> {
	/**
	 * Constructor parameter to specify the default split percentage
	 */
	public static final String CONSTRUCTOR_PARAM_SPLIT_PERCENTAGE = "splitPercentage";
	/**
	 * Constructor parameter to specify the default orientation
	 */
	public static final String CONSTRUCTOR_PARAM_ORIENTATION = "orientation";
	private final double splitPercentage;
	private Orientation orientation;


	/**
	 * Construct a factory to embed the content produced by the wrapped factories in a split pane.
	 * @param splitPercentage the percentage of the split taken up by the top pane (as a percentage between 0.000 and 0.999)
	 * @param orientation the orientation of the split, Vertical unless specified otherwise
	 */
	public SplitPaneFactory(Double splitPercentage, Orientation orientation) {
		super();
		this.splitPercentage = splitPercentage == null ? 0.5 : splitPercentage;
		this.orientation = orientation == null ? Orientation.VERTICAL : orientation;
	}

	/**
	 * Construct a factory to embed the content produced by the wrapped factories in a split pane.
	 * @param splitPercentage the percentage of the split taken up by the top pane (as a percentage between 0.000 and 0.999)
	 * @param factories the factories producing the split pane content
	 * @deprecated Use ControlsFactory#make(SplitPane.class)
	 */
	@Deprecated
	public SplitPaneFactory(Double splitPercentage, NodeFactory<? extends Region>... factories) {
		super(factories);
		this.splitPercentage = splitPercentage == null ? 0.5 : splitPercentage;
	}

	@Override
	public SplitPane makeNode(LocationContext lc) throws CoreException {
		List<Region> nodes = super.makeChildren(lc);

		SplitPane splitPane = new SplitPane(nodes.toArray(new Node[nodes.size()]));
		splitPane.setOrientation(orientation);
		splitPane.setDividerPositions(splitPercentage);

		boolean first = true;
		for (Region region : nodes) {
			if (first){
				VBox.setVgrow(region, Priority.NEVER);
				//Constrain max size of top component:
				region.maxHeightProperty().bind(splitPane.heightProperty().multiply(splitPercentage));
				region.minWidthProperty().bind(splitPane.maxWidthProperty());
			} else {
				VBox.setVgrow(region, Priority.ALWAYS);
			}
		}

		return splitPane;
	}

}
