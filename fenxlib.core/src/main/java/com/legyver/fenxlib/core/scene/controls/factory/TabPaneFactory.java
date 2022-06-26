package com.legyver.fenxlib.core.scene.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.controls.factory.NodeFactory;
import com.legyver.fenxlib.core.scene.controls.options.TabPaneOptions;
import com.legyver.fenxlib.core.util.LocationContextOperator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

import java.util.List;

/**
 * Factory to create a TabPane
 */
public class TabPaneFactory implements NodeFactory<TabPane, TabPaneOptions> {

	/**
	 * Create a new TabPane
	 * @return a new TabPane
	 */
	protected TabPane newTabPane() {
		return new TabPane();
	}

	@Override
	public TabPane makeNode(LocationContext locationContext, TabPaneOptions options) throws CoreException {
		TabPane tabPane = newTabPane();

		LocationContextDecorator decoratedContext = new LocationContextDecorator(locationContext);
		decoratedContext.setName(options.getName());
		ApplicationContext.getComponentRegistry().register(decoratedContext, tabPane);

		List<Tab> tabs = options.getTabs();
		if (tabs != null) {
			for (int i = 0; i < tabs.size(); i++) {
				Tab tab = tabs.get(i);
				new LocationContextOperator(tab).reRegister(decoratedContext, "tab_" + options.getName() + "_content_" + i);
			}
			tabPane.getTabs().addAll(tabs);
		}

		return tabPane;
	}

	@Override
	public TabPaneOptions newOptions() {
		return new TabPaneOptions();
	}
}
