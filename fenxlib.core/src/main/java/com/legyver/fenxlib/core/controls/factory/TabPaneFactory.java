package com.legyver.fenxlib.core.controls.factory;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

/**
 * Factory to create a TabPane
 */
public class TabPaneFactory extends AbstractTabPaneFactory<TabPane> {

	/**
	 * Construct a TabPaneFactory to create a TabPane with Tabs supplied by the child factories
	 * @param name the name for the TabPane used to give context when registering child nodes
	 * @param contentFactory  the content factories for any tabs
	 * @deprecated Use ControlsFactory.make(TabPane.class, MapBuilder.of(TabPane.TABS, yourTabsHere).build())
	 */
	@Deprecated
	public TabPaneFactory(String name, TabContentFactory<Tab>... contentFactory) {
		super(name, contentFactory);
	}

	/**
	 * Construct a TabPaneFactory to create a TabPane with Tabs supplied by the child factories
	 * @param contentFactory factories producing the Tab content
	 * @deprecated Use ControlsFactory.make(TabPane.class, MapBuilder.of(TabPane.TABS, yourTabsHere).build())
	 */
	@Deprecated
	public TabPaneFactory(TabContentFactory<Tab>... contentFactory) {
		super(contentFactory);
	}


	/**
	 * Construct an AbstractTabPaneFactory wrapping optional tab content.
	 * Note: the name defaults to {@link #DEFAULT_NAME}
	 * @param tabs any tabs to be included in this tab pane
	 */
	public TabPaneFactory(List<? extends Tab> tabs) {
		super(tabs);
	}

	/**
	 * Create a new TabPane
	 * @return a new TabPane
	 */
	@Override
	protected TabPane newTabPane() {
		return new TabPane();
	}

}
