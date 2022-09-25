package com.legyver.fenxlib.widgets.filetree.menu;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.menu.section.MenuSection;
import com.legyver.fenxlib.core.menu.templates.section.AbstractMenuSection;
import com.legyver.fenxlib.widgets.filetree.registry.FileTreeRegistry;

import java.util.Arrays;

/**
 * Menu section containing the import directory option
 */
public class ImportMenuSection extends AbstractMenuSection implements MenuSection {

    /**
     * Construct a menu section that provides the ability to import a directory
     * @param fileTreeRegistry the registry to import the file with
     * @throws CoreException if there is an exception creating the menu item
     */
    public ImportMenuSection(FileTreeRegistry fileTreeRegistry) throws CoreException {
        super(Arrays.asList(
                new ImportDirectoryMenuItemProducer(fileTreeRegistry)
        ));
    }
}
