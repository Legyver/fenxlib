package com.legyver.fenxlib.core.impl.factory;

import com.jfoenix.controls.JFXListView;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.factory.options.SizeOptions;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;

/**
 * Factory to create a JFXListView
 */
public class JFXListViewFactory implements NodeFactory<JFXListView> {
	private final String name;
	private final SizeOptions sizeOptions;

	/**
	 * Construct a factory to create a JFXListView
	 * @param name name of the list view
	 * @param sizeOptions size options for the ListView
	 */
	public JFXListViewFactory(String name, SizeOptions sizeOptions) {
		this.name = name;
		this.sizeOptions = sizeOptions;
	}

	@Override
	public JFXListView makeNode(LocationContext locationContext) {
		JFXListView listView = new JFXListView();
		listView.minWidth(sizeOptions.getMinWidth());
		listView.setPrefWidth(sizeOptions.getPrefWidth());
		listView.setMinHeight(sizeOptions.getMinHeight());

		LocationContextDecorator decoratedContext = new LocationContextDecorator(locationContext);
		decoratedContext.setName(name);
		ApplicationContext.getComponentRegistry().register(decoratedContext, listView);
		return listView;
	}

}
