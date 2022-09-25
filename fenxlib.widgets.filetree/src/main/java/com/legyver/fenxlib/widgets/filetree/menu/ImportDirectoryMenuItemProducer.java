package com.legyver.fenxlib.widgets.filetree.menu;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import com.legyver.fenxlib.core.menu.options.AbstractMenuItemProducer;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;

/**
 * Producer to produce an 'Import Directory' menu item
 */
public class ImportDirectoryMenuItemProducer extends AbstractMenuItemProducer {
    /**
     * Construct an option for a menu item with the specified label and event handler
     *
     * @param fileTreeRegistry the file tree registry to register the imported directory with
     * @throws CoreException if there is an error producing the event handler that launches the file browser
     */
    public ImportDirectoryMenuItemProducer(FileTreeRegistry fileTreeRegistry) throws CoreException {
        this(new ImportMenuItemFactory().importDirectoryMenuItemOptions(fileTreeRegistry));
    }

    /**
     * Construct an option for a menu item with the specified label and event handler
     *
     * @param menuItemOptions the menu item options to produce the menu item
     */
    @SuppressWarnings("unchecked")
    public ImportDirectoryMenuItemProducer(MenuItemOptions menuItemOptions) {
        super(menuItemOptions.getText(), menuItemOptions.getEventHandler());
    }
}
