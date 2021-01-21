package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.scene.control.ListView;

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
