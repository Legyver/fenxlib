package com.legyver.fenxlib.core.impl.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.api.factory.NodeFactory;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;

import java.util.List;

import com.legyver.fenxlib.core.api.locator.LocationContextDecorator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Abstract factory for constructing {@link TabPane}
 * @param <T> type of TabPane
 */
public abstract class AbstractTabPaneFactory<T extends TabPane> extends AbstractWrappingFactory<Tab> implements NodeFactory<T> {
	/**
	 * The default name for tabs components.
	 */
	public static final String DEFAULT_NAME = "_TABS";
	/**
	 * The name of the tab.
	 * Used in constructing the LocationContext when registering components
	 */
	private final String name;

	/**
	 * Construct an AbstractTabPaneFactory wrapping optional tab content factories
	 * @param name the name of the tab
	 * @param contentFactories any factories that supply the tab content
	 */
	protected AbstractTabPaneFactory(String name, TabContentFactory<? extends Tab>...contentFactories) {
		super(contentFactories);
		this.name = name;
	}

	/**
	 * Construct an AbstractTabPaneFactory wrapping optional tab content factories.
	 * Note: the name defaults to {@link #DEFAULT_NAME}
	 * @param contentFactories any factories that supply the tab content
	 */
	protected AbstractTabPaneFactory(TabContentFactory<? extends Tab>...contentFactories) {
		this(DEFAULT_NAME, contentFactories);
	}

	@Override
	public T makeNode(LocationContext locationContext) throws CoreException {
		T tabPane = newTabPane();

		LocationContextDecorator decoratedContext = new LocationContextDecorator(locationContext);
		decoratedContext.setName(name);
		ApplicationContext.getComponentRegistry().register(decoratedContext, tabPane);

		List<Tab> tabs = makeChildren(decoratedContext);
		tabPane.getTabs().addAll(tabs);
		return tabPane;
	}

	/**
	 * Construct a new TabPane instance
	 * @return the TabPane
	 */
	protected abstract T newTabPane();

}
