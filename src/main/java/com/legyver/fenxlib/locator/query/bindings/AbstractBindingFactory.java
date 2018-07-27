package com.legyver.fenxlib.locator.query.bindings;

import java.util.Optional;
import java.util.function.Consumer;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import com.legyver.fenxlib.locator.query.ComponentQuery;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.DatePicker;

public abstract class AbstractBindingFactory {

	protected void bindTextField(StringProperty property, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<TextField> text = finalizeQuery(query, named).execute();
		text.get().textProperty().bind(property);
	}

	private ComponentQuery finalizeQuery(ComponentQuery.RegionQueryBuilder query, String named) {
		if (named == null) {
			return query.only();
		} else {
			return query.named(named);
		}
	}

	protected void bindDatePicker(ObjectProperty<LocalDate> property, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<DatePicker> picker = finalizeQuery(query, named).execute();;
		picker.get().valueProperty().bind(property);
	}

	protected void bindListView(Consumer<ObservableList<String>> setter, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<ListView> listView = finalizeQuery(query, named).execute();
		setter.accept(listView.get().getItems());
	}

}
