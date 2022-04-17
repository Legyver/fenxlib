package com.legyver.fenxlib.core.controls.factory;

import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.locator.LocationContext;
import javafx.scene.control.ListView;

/**
 * Factory to create a ListView
 */
public class ListViewFactory implements StyleableFactory<ListView> {
	/**
	 * Constructor parameter to indicate if the ListView should be editable
	 */
	public static final String IS_EDITABLE = "is_editable";
	private final boolean isEditable;

	/**
	 * Construct a factory to create a ListView
	 * @param isEditable if the ListView is editable
	 */
	public ListViewFactory(boolean isEditable) {
		this.isEditable = isEditable;
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
