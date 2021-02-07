package com.legyver.fenxlib.core.impl.factory;

import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import javafx.scene.control.Slider;

public enum SliderFactory implements NodeFactory<Slider> {
	INSTANCE;

	@Override
	public Slider makeNode(LocationContext locationContext) {
		return new Slider();
	}
}
