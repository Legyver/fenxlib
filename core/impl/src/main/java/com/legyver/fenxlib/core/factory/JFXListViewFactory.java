package com.legyver.fenxlib.core.factory;

import com.jfoenix.controls.JFXListView;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.factory.options.SizeOptions;
import com.legyver.fenxlib.core.locator.LocationContext;
import com.legyver.fenxlib.core.locator.LocationContextDecorator;

public class JFXListViewFactory implements NodeFactory<JFXListView> {
	private final String name;
	private final SizeOptions sizeOptions;

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
