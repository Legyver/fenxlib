package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.factory.options.RegionInitializationOptions;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import static com.legyver.core.exception.CoreException.unwrap;
import static com.legyver.core.exception.CoreException.wrap;

public class AccordionMenuFactory implements RegionFactory<Accordion, RegionInitializationOptions> {
	private final TitledPaneFactory[] titledPaneFactories;

	public AccordionMenuFactory(TitledPaneFactory... titledPaneFactories) {
		this.titledPaneFactories = titledPaneFactories;
	}

	@Override
	public Accordion makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
		List<TitledPane> panes = unwrap(() -> Stream.of(titledPaneFactories)
				.map(factory -> wrap(() -> factory.makeTitlePane(regionInitOptions.getLocationContext())))
				.collect(Collectors.toList()));

		Accordion sideMenu = new Accordion(panes.toArray(new TitledPane[panes.size()]));
		GridPane.setHgrow(sideMenu, Priority.SOMETIMES);
		GridPane.setVgrow(sideMenu, Priority.SOMETIMES);
		sideMenu.setExpandedPane(panes.iterator().next());
		return sideMenu;
	}

}
