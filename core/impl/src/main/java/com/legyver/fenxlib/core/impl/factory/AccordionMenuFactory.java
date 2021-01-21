package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.impl.factory.options.RegionInitializationOptions;

import java.util.List;

import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AccordionMenuFactory extends AbstractWrappingFactory<TitledPane> implements RegionFactory<Accordion, RegionInitializationOptions> {

	public AccordionMenuFactory(TitledPaneFactory... titledPaneFactories) {
		super(titledPaneFactories);
	}

	@Override
	public Accordion makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
		List<TitledPane> panes = makeChildren(regionInitOptions.getLocationContext());

		Accordion sideMenu = new Accordion(panes.toArray(new TitledPane[panes.size()]));
		GridPane.setHgrow(sideMenu, Priority.SOMETIMES);
		GridPane.setVgrow(sideMenu, Priority.SOMETIMES);
		sideMenu.setExpandedPane(panes.iterator().next());
		return sideMenu;
	}

}
