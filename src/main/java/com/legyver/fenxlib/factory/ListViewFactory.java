package com.legyver.fenxlib.factory;

import javafx.scene.control.ListView;
import com.legyver.fenxlib.locator.LocationContext;
import com.legyver.fenxlib.util.GuiUtil;

public class ListViewFactory implements NodeFactory<ListView> {
	private final boolean isEditable;

	public ListViewFactory(boolean isEditable) {
		this.isEditable = isEditable;
	}

	@Override
	public ListView makeNode(LocationContext locationContext) {
		ListView listView = new ListView<>();
		listView.setEditable(isEditable);
		GuiUtil.getComponentRegistry().register(locationContext, listView);
		return listView;
	}
}
