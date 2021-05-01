package com.legyver.fenxlib.core.impl.locator.query.bindings;

import com.legyver.fenxlib.core.api.locator.query.IRegionDiscriminator;
import com.legyver.fenxlib.core.api.locator.query.bindings.AbstractBindingFactory;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * Binds a ListView (locatable by a ComponentQuery and name) to an ObservableList
 */
public interface ListViewBindingFactory extends AbstractBindingFactory {
	default void bindListView(Consumer<ObservableList<String>> setter, IRegionDiscriminator query, String named) {
		Optional<ListView> listView = finalizeQuery(query, named).execute();
		setter.accept(listView.get().getItems());
	}
}
