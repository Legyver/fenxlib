package com.legyver.fenxlib.core.impl.widget;

import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.binding.When;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.StringProperty;

public class OnOffSlider extends JFXToggleButton {
	private final ReadOnlyStringWrapper selectedOption = new ReadOnlyStringWrapper();

	public OnOffSlider(String on, String off) {
		super();
		super.setHeight(2);
		selectedOption.bind(new When(selectedProperty()).then(on).otherwise(off));
	}

	public StringProperty selectedOptionProperty() {
		return selectedOption;
	}
}
