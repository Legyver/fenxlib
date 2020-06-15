package com.legyver.fenxlib.factory;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TabPaneFactory extends AbstractTabPaneFactory<TabPane> {

	public TabPaneFactory(String name, TabContentFactory<Tab>... contentFactory) {
		super(name, contentFactory);
	}

	public TabPaneFactory(TabContentFactory<Tab>... contentFactory) {
		super(contentFactory);
	}

	@Override
	protected TabPane newTabPane() {
		return new TabPane();
	}

}
