package com.legyver.fenxlib.factory;

import com.jfoenix.controls.JFXListView;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.LocationContextDecorator;
import com.legyver.fenxlib.util.GuiUtil;

public class JFXListViewFactory implements NodeFactory<JFXListView> {
	private final String name;

	public JFXListViewFactory(String name) {
		this.name = name;
	}

	@Override
	public JFXListView makeNode(LocationContext locationContext) {
		JFXListView listView = new JFXListView();
		listView.minWidth(250);
		listView.setPrefWidth(300);
		listView.maxWidth(350);
		listView.setMinHeight(50);

		LocationContextDecorator decoratedContext = new LocationContextDecorator(locationContext);
		decoratedContext.setName(name);
		GuiUtil.getComponentRegistry().register(decoratedContext, listView);
		return listView;
	}

}
