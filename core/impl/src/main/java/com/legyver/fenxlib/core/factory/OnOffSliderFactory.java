package com.legyver.fenxlib.core.factory;

import com.legyver.fenxlib.core.widget.OnOffSlider;
import javafx.beans.property.StringProperty;
import com.legyver.fenxlib.core.locator.LocationContext;

public class OnOffSliderFactory implements NodeFactory<OnOffSlider>, BindableValueFactory {

	private final String onValue;
	private final String offValue;
	private OnOffSlider made;

	public OnOffSliderFactory(String onValue, String offValue) {
		this.onValue = onValue;
		this.offValue = offValue;
	}

	@Override
	public OnOffSlider makeNode(LocationContext locationContext) {
		return makeNode();
	}

	public OnOffSlider makeNode() {
		if (made == null) {
			makeNodeSync();
		}
		return made;
	}

	private synchronized void makeNodeSync() {
		if (made == null) {
			made = new OnOffSlider(onValue, offValue);
		}
	}

	@Override
	public StringProperty boundProperty() {
		return makeNode().selectedOptionProperty();
	}

}