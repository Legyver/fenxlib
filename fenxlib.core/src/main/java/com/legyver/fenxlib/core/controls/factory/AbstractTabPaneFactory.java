package com.legyver.fenxlib.core.controls.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;

import java.util.List;

import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Abstract factory for constructing {@link TabPane}
 * @param <T> type of TabPane
 */
public abstract class AbstractTabPaneFactory<T extends TabPane> extends AbstractWrappingFactory<Tab> implements StyleableFactory<T> {
	/**
	 * The default name for tabs components.
	 */
	public static final String DEFAULT_NAME = "_TABS";
	/**
	 * The constructor-injected tabs
	 */
	public static final String TABS = "tabs";

	/**
	 * The name of the tab.
	 * Used in constructing the LocationContext when registering components
	 */
	private final String name;

	private List<? extends Tab> tabs;

	/**
	 * Construct an AbstractTabPaneFactory wrapping optional tab content factories
	 * @param name the name of the tab
	 * @param contentFactories any factories that supply the tab content
	 * @deprecated Use ControlsFactory.make(TabPane.class, MapBuilder.of(AbstractTabPaneFactory.TABS, yourTabsHere).build())
	 */
	@Deprecated
	protected AbstractTabPaneFactory(String name, TabContentFactory<? extends Tab>...contentFactories) {
		super(contentFactories);
		this.name = name;
	}

	/**
	 * Construct an AbstractTabPaneFactory wrapping optional tab content factories.
	 * Note: the name defaults to {@link #DEFAULT_NAME}
	 * @param contentFactories any factories that supply the tab content
	 * @deprecated Use ControlsFactory.make(TabPane.class, MapBuilder.of(AbstractTabPaneFactory.TABS, yourTabsHere).build())
	 */
	@Deprecated
	protected AbstractTabPaneFactory(TabContentFactory<? extends Tab>...contentFactories) {
		this(DEFAULT_NAME, contentFactories);
	}

	/**
	 * Construct an AbstractTabPaneFactory wrapping optional tab content.
	 * Note: the name defaults to {@link #DEFAULT_NAME}
	 * @param tabs any tabs to be included in this tab pane
	 */
	protected AbstractTabPaneFactory(List<? extends Tab> tabs) {
		this.tabs = tabs;
		this.name = DEFAULT_NAME;
	}

	@Override
	public T makeNode(LocationContext locationContext) throws CoreException {
		T tabPane = newTabPane();

		LocationContextDecorator decoratedContext = new LocationContextDecorator(locationContext);
		decoratedContext.setName(name);
		ApplicationContext.getComponentRegistry().register(decoratedContext, tabPane);

		if (tabs == null) {
			tabs = makeChildren(decoratedContext);
		}
		tabPane.getTabs().addAll(tabs);
		return tabPane;
	}

	/**
	 * Construct a new TabPane instance
	 * @return the TabPane
	 */
	protected abstract T newTabPane();

}
