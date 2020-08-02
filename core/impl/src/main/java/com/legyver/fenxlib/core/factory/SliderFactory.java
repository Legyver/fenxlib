package com.legyver.fenxlib.core.factory;

import javafx.scene.control.Slider;
import com.legyver.fenxlib.core.locator.LocationContext;

public enum SliderFactory implements NodeFactory<Slider> {
	INSTANCE;

	@Override
	public Slider makeNode(LocationContext locationContext) {
		return new Slider();
	}
}
