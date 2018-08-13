package com.legyver.fenxlib.locator.query.bindings;

import com.legyver.fenxlib.locator.query.ComponentQuery;
import com.legyver.fenxlib.skin.SkinnableNumberField;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Consumer;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public abstract class AbstractBindingFactory {

	protected void bindTextField(StringProperty property, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<TextField> text = finalizeQuery(query, named).execute();
		String value = property.get();
		property.bindBidirectional(text.get().textProperty());
		if (value == null) {
			text.get().clear();
		} else {
			text.get().setText(value);
		}
	}

	private ComponentQuery finalizeQuery(ComponentQuery.RegionQueryBuilder query, String named) {
		if (named == null) {
			return query.only();
		} else {
			return query.named(named);
		}
	}

	protected void bindTextField(ObjectProperty<BigDecimal> property, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<TextField> text = finalizeQuery(query, named).execute();
		TextField textField = text.get();
		if (textField instanceof SkinnableNumberField) {
			SkinnableNumberField numberField = (SkinnableNumberField) textField;
			BigDecimal value = property.get();
			property.bindBidirectional(numberField.valueProperty());
			numberField.setValue(value);
		}
	}

	protected void bindDatePicker(ObjectProperty<LocalDate> property, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<DatePicker> picker = finalizeQuery(query, named).execute();
		LocalDate value = property.get();
		property.bind(picker.get().valueProperty());
		picker.get().setValue(value);
	}

	protected void bindListView(Consumer<ObservableList<String>> setter, ComponentQuery.RegionQueryBuilder query, String named) {
		Optional<ListView> listView = finalizeQuery(query, named).execute();
		setter.accept(listView.get().getItems());
	}

}
