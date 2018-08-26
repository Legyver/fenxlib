package com.legyver.fenxlib.factory;

import com.jfoenix.controls.JFXListView;
import com.legyver.fenxlib.factory.options.SizeOptions;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.locator.LocationContextDecorator;
import com.legyver.fenxlib.util.GuiUtil;

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
		listView.setPrefHeight(sizeOptions.getPrefHeight());
		LocationContextDecorator decoratedContext = new LocationContextDecorator(locationContext);
		decoratedContext.setName(name);
		GuiUtil.getComponentRegistry().register(decoratedContext, listView);
		return listView;
	}

}
