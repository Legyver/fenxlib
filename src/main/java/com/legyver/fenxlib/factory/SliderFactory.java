package com.legyver.fenxlib.factory;

import javafx.scene.control.Slider;
import com.legyver.fenxlib.locator.LocationContext;

public enum SliderFactory implements NodeFactory<Slider> {
	INSTANCE;

	@Override
	public Slider makeNode(LocationContext locationContext) {
		return new Slider();
	}
}
