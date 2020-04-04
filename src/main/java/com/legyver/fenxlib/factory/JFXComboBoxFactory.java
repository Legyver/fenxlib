package com.legyver.fenxlib.factory;

import com.jfoenix.controls.JFXComboBox;
import com.legyver.fenxlib.locator.LocationContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.ObservableList;

public class JFXComboBoxFactory implements NodeFactory<JFXComboBox> {

	private List<String> items;

	public JFXComboBoxFactory(List<String> items) {
		this.items = items;
	}

	public JFXComboBoxFactory(String... items) {
		this(items == null ? Collections.EMPTY_LIST : Arrays.asList(items));
	}

	@Override
	public JFXComboBox makeNode(LocationContext locationContext) {
		JFXComboBox comboBox = new JFXComboBox();
		if (items != null) {
			ObservableList list = comboBox.getItems();
			Stream.of(items).forEach(s -> list.add(s));
		}
		return comboBox;
	}

}
