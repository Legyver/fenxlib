package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.RegionInitializationOptions;
import com.legyver.fenxlib.locator.LocationContext;
import java.util.List;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class AccordionMenuFactory extends AbstractWrappingFactory<TitledPane> implements RegionFactory<Accordion, RegionInitializationOptions>, NodeFactory<Accordion> {

	public AccordionMenuFactory(TitledPaneFactory... titledPaneFactories) {
		super(titledPaneFactories);
	}

	@Override
	public Accordion makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
		return makeAccordion(regionInitOptions.getLocationContext());
	}

	private Accordion makeAccordion(LocationContext locationContext) throws CoreException {
		List<TitledPane> panes = makeChildren(locationContext);

		Accordion sideMenu = new Accordion(panes.toArray(new TitledPane[panes.size()]));
		GridPane.setHgrow(sideMenu, Priority.SOMETIMES);
		GridPane.setVgrow(sideMenu, Priority.SOMETIMES);
		sideMenu.setExpandedPane(panes.iterator().next());
		return sideMenu;
	}

	@Override
	public Accordion makeNode(LocationContext locationContext) throws CoreException {
		return makeAccordion(locationContext);
	}

}
