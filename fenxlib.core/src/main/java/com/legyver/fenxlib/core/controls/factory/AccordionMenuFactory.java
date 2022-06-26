package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;

import java.util.List;

import com.legyver.fenxlib.core.layout.factory.RegionFactory;
import com.legyver.fenxlib.core.layout.options.RegionInitializationOptions;
import com.legyver.fenxlib.core.scene.controls.factory.TitledPaneFactory;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * Factory to create an accordion menu
 *  Creates using the default {@link Accordion}
 */
public class AccordionMenuFactory implements RegionFactory<Accordion, RegionInitializationOptions> {

//	/**
//	 * Construct a factory for accordion menus
//	 * @param titledPaneFactories titled pane factories for accordion menu sections
//	 */
//	public AccordionMenuFactory(TitledPaneFactory... titledPaneFactories) {
//		super(titledPaneFactories);
//	}

	@Override
	public Accordion makeRegion(BorderPane borderPane, RegionInitializationOptions regionInitOptions) throws CoreException {
//		List<TitledPane> panes = makeChildren(regionInitOptions.getLocationContext());

//		Accordion sideMenu = makeAccordion(panes);
//		GridPane.setHgrow(sideMenu, Priority.SOMETIMES);
//		GridPane.setVgrow(sideMenu, Priority.SOMETIMES);
//		sideMenu.setExpandedPane(panes.iterator().next());
		return null;
	}

	/**
	 * Factory method to create the accordion.
	 * Provides the default javafx accordion.  Override if you want something different
	 * @param panes titled panes for the accordion menu
	 * @return the accordion menu
	 */
	protected Accordion makeAccordion(List<TitledPane> panes) {
		return new Accordion(panes.toArray(new TitledPane[panes.size()]));
	}

}
