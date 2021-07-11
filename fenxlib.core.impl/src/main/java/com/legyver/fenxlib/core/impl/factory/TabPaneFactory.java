package com.legyver.fenxlib.core.impl.factory;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Factory to create a TabPane
 */
public class TabPaneFactory extends AbstractTabPaneFactory<TabPane> {

	/**
	 * Construct a TabPaneFactory to create a TabPane with Tabs supplied by the child factories
	 * @param name the name for the TabPane used to give context when registering child nodes
	 * @param contentFactory  the content factories for any tabs
	 */
	public TabPaneFactory(String name, TabContentFactory<Tab>... contentFactory) {
		super(name, contentFactory);
	}

	/**
	 * Construct a TabPaneFactory to create a TabPane with Tabs supplied by the child factories
	 * @param contentFactory factories producing the Tab content
	 */
	public TabPaneFactory(TabContentFactory<Tab>... contentFactory) {
		super(contentFactory);
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
