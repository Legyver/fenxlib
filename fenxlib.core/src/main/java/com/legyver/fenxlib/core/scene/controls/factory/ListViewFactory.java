package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.core.scene.controls.options.ListViewOptions;
import javafx.scene.control.ListView;

import java.util.UUID;

/**
 * Factory to create a ListView
 */
public class ListViewFactory implements NodeFactory<ListView, ListViewOptions> {

	@Override
	public ListView makeNode(LocationContext locationContext, ListViewOptions options) {
		ListView listView = makeListView();
		options.editableAdapter().setNotNull((editable) -> listView.setEditable(editable));
		ApplicationContext.getComponentRegistry().register(locationContext.decorateWith(options.getName()), listView);
		return listView;
	}

	@Override
	public ListViewOptions newOptions() {
		return new ListViewOptions();
	}

	/**
	 * Create a generic ListView.  Override if you want something else.
	 * @return a generic ListView.
	 */
	protected ListView makeListView() {
		return new ListView();
	}

}
