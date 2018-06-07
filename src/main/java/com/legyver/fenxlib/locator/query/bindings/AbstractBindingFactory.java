package com.legyver.fenxlib.locator.query.bindings;

import java.util.Optional;
import java.util.function.Consumer;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.legyver.fenxlib.locator.query.ComponentQuery;

public abstract class AbstractBindingFactory {

	protected void bindTextField(StringProperty property, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<TextField> text;
		if (named == null) {
			text = query.only().execute();
		} else {
			text = query.named(named).execute();
		}
		text.get().textProperty().bind(property);
	}

	protected void bindListView(Consumer<ObservableList<String>> setter, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<ListView> listView;
		if (named == null) {
			listView = query.only().execute();
		} else {
			listView = query.named(named).execute();
		}
		setter.accept(listView.get().getItems());
	}

}
