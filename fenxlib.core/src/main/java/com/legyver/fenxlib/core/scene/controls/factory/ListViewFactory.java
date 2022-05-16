package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import javafx.scene.control.ListView;

/**
 * Factory to create a ListView
 */
public class ListViewFactory implements NodeFactory<ListView> {
	/**
	 * Constructor parameter to indicate if the ListView should be editable
	 */
	public static final String IS_EDITABLE = "is_editable";
	private final boolean isEditable;

	/**
	 * Construct a factory to create a ListView
	 * @param isEditable if the ListView is editable
	 */
	public ListViewFactory(Boolean isEditable) {
		this.isEditable = isEditable != null ? isEditable : false;
	}

	@Override
	public ListView makeNode(LocationContext locationContext) {
		ListView listView = makeListView();
		listView.setEditable(isEditable);
		ApplicationContext.getComponentRegistry().register(locationContext, listView);
		return listView;
	}

	/**
	 * Create a generic ListView.  Override if you want something else.
	 * @return a generic ListView.
	 */
	protected ListView makeListView() {
		return new ListView();
	}

}
