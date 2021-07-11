package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.api.factory.TitledPaneContentFactory;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;



/**
 * Factory to create a TitledPane
 *
 */
public class TitledPaneFactory<T extends Pane> implements NodeFactory<TitledPane> {
	private final String title;
	private final TitledPaneContentFactory<T> contentFactory;

	/**
	 * Construct a TitledPaneFactory with a given title and contentFactory
	 * @param title the title of the TitledPane
	 * @param contentFactory content factory that will create the content of the TitledPane
	 */
	public TitledPaneFactory(String title, TitledPaneContentFactory<T> contentFactory) {
		this.title = title;
		this.contentFactory = contentFactory;
	}

	/**
	 * Make a TitledPane.
	 * The name of the titled pane is passed down to any child factories so child elements can be located within this titled pane.
	 * @param locationContext the parent location context
	 * @return the TitledPane
	 * @throws CoreException if there is an Exception raised by the content factory
	 */
	public TitledPane makeTitlePane(LocationContext locationContext) throws CoreException {
		LocationContextDecorator decorated = new LocationContextDecorator(locationContext);
		decorated.setName(title);
		return makeAccordionPane(title, contentFactory.makeNode(decorated));
	}

	private TitledPane makeAccordionPane(String title, Pane grid) {
		Region spacer = new Region();
		spacer.setMinSize(200, 10);

		grid.getChildren().add(spacer);
		TitledPane history = new TitledPane(title, grid);
		return history;
	}

	@Override
	public TitledPane makeNode(LocationContext locationContext) throws CoreException {
		return makeTitlePane(locationContext);
	}
}
