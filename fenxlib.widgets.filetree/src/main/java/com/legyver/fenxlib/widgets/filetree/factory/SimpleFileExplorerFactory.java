package com.legyver.fenxlib.widgets.filetree.factory;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.icons.application.IconAliasMap;
import com.legyver.fenxlib.core.controls.factory.StyleableFactory;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.widgets.filetree.SimpleFileExplorer;
import com.legyver.fenxlib.widgets.filetree.icons.DefaultTreeIcons;
import javafx.scene.control.ContextMenu;

/**
 * Factory to create a {@link SimpleFileExplorer}
 */
public class SimpleFileExplorerFactory implements StyleableFactory<SimpleFileExplorer, SimpleFileExplorerOptions> {

    /**
     * Construct a factory to produce File Explorers with the specified tree icons
     * @param fileTreeIcons the tree icons to use
     */
    public SimpleFileExplorerFactory(IconAliasMap fileTreeIcons) {
        IconAliasMap applicationIcons = ApplicationContext.getIconAliasMap();
        applicationIcons.merge(fileTreeIcons);
    }

    /**
     * Construct a factory to produce File Explorers with the default tree icons
     */
    public SimpleFileExplorerFactory() {
        this(DefaultTreeIcons.defaultTreeIcons().build());
    }

    /**
     * Make the SimpleFileExplorer.
     * @param locationContext the (parent) location context to register the widget under
     * @return the new FileExplorer
     */
    @Override
    public SimpleFileExplorer makeNode(LocationContext locationContext, SimpleFileExplorerOptions options) throws CoreException {
        ContextMenu areaContextMenu = options.getAreaContextMenuFactory().makeMenu(locationContext);
        LocationContext decoratedLocationContext = new LocationContextDecorator(locationContext);
        decoratedLocationContext.setName(options.getName());

        SimpleFileExplorer simpleFileExplorer = new SimpleFileExplorer(options.getFileTreeRegistry(), options.getFileWatchHandler(), areaContextMenu);
        ApplicationContext.getComponentRegistry().register(decoratedLocationContext, simpleFileExplorer);
        return simpleFileExplorer;
    }

    @Override
    public SimpleFileExplorerOptions newOptions() throws CoreException {
        return new SimpleFileExplorerOptions();
    }
}
