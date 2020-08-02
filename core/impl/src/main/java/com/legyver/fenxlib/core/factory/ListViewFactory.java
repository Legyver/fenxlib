package com.legyver.fenxlib.core.factory;

import com.legyver.fenxlib.core.context.ApplicationContext;
import javafx.scene.control.ListView;
import com.legyver.fenxlib.core.locator.LocationContext;

public class ListViewFactory implements NodeFactory<ListView> {
	private final boolean isEditable;

	public ListViewFactory(boolean isEditable) {
		this.isEditable = isEditable;
	}

	@Override
	public ListView makeNode(LocationContext locationContext) {
		ListView listView = new ListView<>();
		listView.setEditable(isEditable);
		ApplicationContext.getComponentRegistry().register(locationContext, listView);
		return listView;
	}
}
