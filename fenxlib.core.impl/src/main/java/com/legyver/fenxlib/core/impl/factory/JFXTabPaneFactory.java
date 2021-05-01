package com.legyver.fenxlib.core.impl.factory;

import com.jfoenix.controls.JFXTabPane;
import javafx.scene.control.Tab;

/**
 * Factory for creating TabPanes of type JFXTabPane
 */
public class JFXTabPaneFactory extends AbstractTabPaneFactory<JFXTabPane> {

	/**
	 * Construct a JFXTabPaneFactory that uses the specified name and tab content factories
	 * @param name the name of the tab pane
	 * @param contentFactory the tab content factories
	 */
	public JFXTabPaneFactory(String name, TabContentFactory<? extends Tab>... contentFactory) {
		super(name, contentFactory);
	}

	/**
	 * Construct a JFXTabPaneFactory that uses the specified name and tab content factories
	 * @param contentFactory
	 */
	public JFXTabPaneFactory(TabContentFactory<? extends Tab>... contentFactory) {
		super(contentFactory);
	}

	/**
	 * Construct a TabPane of type JFXTabPane
	 * @return the new TabPane
	 */
	@Override
	protected JFXTabPane newTabPane() {
		return new JFXTabPane();
	}
	
}
