package com.legyver.fenxlib.core.locator.query.bindings;

import com.legyver.fenxlib.core.locator.query.ComponentQuery;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Optional;
import java.util.function.Consumer;

public interface ListViewBindingFactory extends AbstractBindingFactory {
	default void bindListView(Consumer<ObservableList<String>> setter, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<ListView> listView = finalizeQuery(query, named).execute();
		setter.accept(listView.get().getItems());
	}
}
