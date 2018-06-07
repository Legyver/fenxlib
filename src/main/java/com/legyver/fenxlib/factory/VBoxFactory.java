package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.legyver.fenxlib.locator.LocationContext;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class VBoxFactory {

	private final HBoxFactory[] hboxFactories;

	public VBoxFactory(HBoxFactory...hboxFactories) {
		this.hboxFactories = hboxFactories;
	}

	public VBox makeVBox(LocationContext locationContext) throws CoreException {
		List<HBox> entries = unwrap(() -> Stream.of(hboxFactories)
				.map(factory -> wrap(() -> factory.makeHBox(locationContext)))
				.collect(Collectors.toList()));
		return new VBox(entries.toArray(new HBox[entries.size()]));
	}
}
