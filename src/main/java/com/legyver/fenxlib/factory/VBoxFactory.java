package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import com.legyver.fenxlib.locator.LocationContext;

public class VBoxFactory extends AbstractWrappingFactory<HBox> implements NodeFactory<VBox> {

	public VBoxFactory(HBoxFactory...hboxFactories) {
		super(hboxFactories);
	}

	public VBox makeVBox(LocationContext locationContext) throws CoreException {
		List<HBox> entries = makeChildren(locationContext);
		return new VBox(entries.toArray(new HBox[entries.size()]));
	}

	@Override
	public VBox makeNode(LocationContext locationContext) throws CoreException {
		return makeVBox(locationContext);
	}
}
