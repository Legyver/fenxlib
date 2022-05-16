package com.legyver.fenxlib.core.locator.query.bindings;

import com.legyver.fenxlib.api.locator.query.IRegionDiscriminator;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Binds a ListView (locatable by a ComponentQuery and name) to an ObservableList
 */
public class ListViewBinding extends BaseBinding {
	/**
	 * Bind a ListView to an observable list property
	 * @param setter the setter that accepts the ObservableList of items of the ListView
	 * @param query the query that identifies the ListView
	 * @param named the (optional) name of the ListView
	 */
	public static void bindListView(Consumer<ObservableList<String>> setter, IRegionDiscriminator query, String named) {
		Optional<ListView> listView = finalizeQuery(query, named).execute();
		setter.accept(listView.get().getItems());
	}
}
