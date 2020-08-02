package com.legyver.fenxlib.core.factory;

import com.legyver.core.exception.CoreException;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;

public class TitledPaneFactory<T extends Pane> implements NodeFactory<TitledPane> {
	private final String title;
	private final TitledPaneContentFactory<T> contentFactory;

	public TitledPaneFactory(String title, TitledPaneContentFactory<T> contentFactory) {
		this.title = title;
		this.contentFactory = contentFactory;
	}

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