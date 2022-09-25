package com.legyver.fenxlib.widgets.filetree.menu;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import com.legyver.fenxlib.core.files.action.OpenDirectoryAction;
import com.legyver.fenxlib.widgets.filetree.event.ImportDirectoryConsumer;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * Factory to consolidate the various menu items that ultimately result in importing a directory to the file tree widget.
 */
public class ImportMenuItemFactory {
    /**
     * The default import directory text property to display on the menu item
     */
    public static final String FILETREE_MENU_IMPORT_DIRECTORY = "legyver.defaults.label.filetree.menu.import.directory";
    /**
     * The default select directory text property to display in the directory selector browser window
     */
    public static final String FILETREE_BROWSER_SELECT_DIRECTORY = "legyver.defaults.label.filetree.browser.select.directory";
    private final String menuItemName;
    private final String selectDirectoryText;

    /**
     * Construct the factory to produce the menu item
     * @param menuItemName the name or property for the menu item
     * @param selectDirectoryText the text or property to display in the select directory browser window
     */
    public ImportMenuItemFactory(String menuItemName, String selectDirectoryText) {
        this.menuItemName = menuItemName;
        this.selectDirectoryText = selectDirectoryText;
    }

    /**
     * Construct the factory to produce the menu item
     * {@link #FILETREE_MENU_IMPORT_DIRECTORY}
     * {@link #FILETREE_BROWSER_SELECT_DIRECTORY}
     */
    public ImportMenuItemFactory() {
        this(FILETREE_MENU_IMPORT_DIRECTORY, FILETREE_BROWSER_SELECT_DIRECTORY);
    }

    /**
     * Create an 'Import Directory' menu item
     * @param fileTreeRegistry the file tree registry the file will be added to
     * @return the menu item
     * @throws CoreException if there is an error
     */
    public MenuItem importDirectoryMenuItem(FileTreeRegistry fileTreeRegistry) throws CoreException {
        return ControlsFactory.make(MenuItem.class, importDirectoryMenuItemOptions(fileTreeRegistry));
    }

    /**
     * Get the menu item options used to create the import action
     * @param fileTreeRegistry the file tree registry the file will be added to
     * @return the options for creating the menu item
     * @throws CoreException if there is an error
     */
    public MenuItemOptions importDirectoryMenuItemOptions(FileTreeRegistry fileTreeRegistry) throws CoreException {
        MenuItemOptions addDirectoryOptions = new MenuItemOptions()
                .text(menuItemName)
                .eventHandler(importDirectoryEventHandler(fileTreeRegistry));
        return addDirectoryOptions;
    }

    /**
     * Get the event handler to import a directory
     * @param fileTreeRegistry the file tree registry to register the directory with
     * @return the event handler
     * @throws CoreException if there is an error producing the file browser
     */
    public EventHandler importDirectoryEventHandler(FileTreeRegistry fileTreeRegistry) throws CoreException {
        return new OpenDirectoryAction(selectDirectoryText, new ImportDirectoryConsumer(fileTreeRegistry));
    }
}
