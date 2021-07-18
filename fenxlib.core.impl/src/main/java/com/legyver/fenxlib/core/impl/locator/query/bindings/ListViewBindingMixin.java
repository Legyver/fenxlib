package com.legyver.fenxlib.core.impl.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.IRegionDiscriminator;
import com.legyver.fenxlib.core.api.locator.query.bindings.AbstractBindingMixin;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Binds a ListView (locatable by a ComponentQuery and name) to an ObservableList
 */
public interface ListViewBindingMixin extends AbstractBindingMixin {
	/**
	 * Bind a ListView to an observable list property
	 * @param setter the setter that accepts the ObservableList of items of the ListView
	 * @param query the query that identifies the ListView
	 * @param named the (optional) name of the ListView
	 */
	default void bindListView(Consumer<ObservableList<String>> setter, IRegionDiscriminator query, String named) {
		Optional<ListView> listView = finalizeQuery(query, named).execute();
		setter.accept(listView.get().getItems());
	}
}
