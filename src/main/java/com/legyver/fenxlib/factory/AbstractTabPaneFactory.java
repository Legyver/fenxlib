package com.legyver.fenxlib.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.locator.LocationContext;
import java.util.List;

import com.legyver.fenxlib.locator.LocationContextDecorator;
import com.legyver.fenxlib.util.GuiUtil;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public abstract class AbstractTabPaneFactory<T extends TabPane> extends AbstractWrappingFactory<Tab> implements NodeFactory<T> {
	public static final String DEFAULT_NAME = "_TABS";
	private final String name;

	protected AbstractTabPaneFactory(String name, TabContentFactory<? extends Tab>...contentFactories) {
		super(contentFactories);
		this.name = name;
	}

	protected AbstractTabPaneFactory(TabContentFactory<? extends Tab>...contentFactories) {
		this(DEFAULT_NAME, contentFactories);
	}

	@Override
	public T makeNode(LocationContext locationContext) throws CoreException {
		T tabPane = newTabPane();

		LocationContextDecorator decoratedContext = new LocationContextDecorator(locationContext);
		decoratedContext.setName(name);
		GuiUtil.getComponentRegistry().register(decoratedContext, tabPane);

		List<Tab> tabs = makeChildren(decoratedContext);
		tabPane.getTabs().addAll(tabs);
		return tabPane;
	}

	protected abstract T newTabPane();

}
