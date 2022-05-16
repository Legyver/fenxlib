package com.legyver.fenxlib.core.menu.templates.section;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.Fenxlib;
import com.legyver.fenxlib.api.config.parts.IRecentlyModified;
import com.legyver.fenxlib.api.config.parts.IRecentlyViewedFile;
import com.legyver.fenxlib.api.regions.ApplicationRegions;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.core.controls.ControlsFactory;
import com.legyver.fenxlib.api.files.DefaultFileOptions;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.locator.DefaultLocationContext;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.locator.LocationContextDecorator;
import com.legyver.fenxlib.core.menu.factory.IMenuItemFactory;
import com.legyver.fenxlib.core.menu.factory.MenuFactory;
import com.legyver.fenxlib.core.menu.operator.MenuOperator;
import com.legyver.fenxlib.core.menu.section.MenuSection;
import com.legyver.fenxlib.core.menu.section.MenuSectionOptions;
import com.legyver.fenxlib.core.menu.templates.file.OpenRecentFileFactory;
import com.legyver.fenxlib.core.util.MapBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Menu section to display recent files that can be opened
 */
public class RecentFilesMenuSection implements MenuSection {
    private static final Logger logger = LogManager.getLogger(RecentFilesMenuSection.class);
    /**
     * The default text to display for a recent files menu section
     */
    public static final String DEFAULT_RECENT_FILES_MENU_NAME = "Open Recent";
    /**
     * The default menu to display the recent files menu on
     */
    public static final String DEFAULT_RECENT_FILES_PARENT_MENU_NAME = "File";
    private final String menuName;
    private final String parentMenuName;
    private final ObservableList<FileOptions> recentFiles;

    /**
     * Construct a menu section to display recent files
     * uses {@link #DEFAULT_RECENT_FILES_MENU_NAME} for the menu section name
     * and {@link #DEFAULT_RECENT_FILES_PARENT_MENU_NAME} for the menu to place it on
     * @throws CoreException if there is an error reading the application config
     */
    public RecentFilesMenuSection() throws CoreException {
        this(DEFAULT_RECENT_FILES_MENU_NAME, DEFAULT_RECENT_FILES_PARENT_MENU_NAME);
    }

    /**
     * Construct a menu section to display recent files
     * @param menuName the menu name
     * @param parentMenuName the parent menu to place this on
     * @throws CoreException if there is a problem reading the application config
     */
    public RecentFilesMenuSection(String menuName, String parentMenuName) throws CoreException {
        this.menuName = menuName != null ? menuName : DEFAULT_RECENT_FILES_MENU_NAME;
        this.parentMenuName = parentMenuName != null ? parentMenuName : DEFAULT_RECENT_FILES_PARENT_MENU_NAME;

        List<FileOptions> recentFileList = getRecentFiles();
        Collections.sort(recentFileList, Comparator.comparing(FileOptions::getFileName));
        recentFiles = FXCollections.observableArrayList(recentFileList);
    }

    private Menu makeFileMenuIfNotExisting(String parentMenuName, LocationContext locationContext) throws CoreException {
        Optional<Menu> fileMenuPreexisting = Fenxlib.getMenu(locationContext);
        Menu fileMenu;
        if (fileMenuPreexisting.isPresent()) {
            fileMenu = fileMenuPreexisting.get();
        } else {
            fileMenu = ControlsFactory.make(Menu.class, locationContext, MapBuilder.of(MenuFactory.PARAM_NAME, parentMenuName).build());
        }
        return fileMenu;
    }

    private List<FileOptions> getRecentFiles() throws CoreException {
        IRecentlyModified recentlyModified = ApplicationContext.getApplicationConfig().getRecentlyModified();
        List<IRecentlyViewedFile> recentlyViewedFiles = recentlyModified.getValues();
        List<FileOptions> recentFileList = new ArrayList<>();
        for (IRecentlyViewedFile recentlyViewedFile : recentlyViewedFiles) {
            FileOptions fileOptions = new DefaultFileOptions();
            fileOptions.setFileName(recentlyViewedFile.getName());
            fileOptions.setFilePath(recentlyViewedFile.getPath());
            recentFileList.add(fileOptions);
        }
        return recentFileList;
    }

    @Override
    public List<? extends IMenuItemFactory> getFactories(MenuSectionOptions menuSectionOptions) throws CoreException {
        LocationContext locationContext = new LocationContextDecorator(new DefaultLocationContext(ApplicationRegions.MENUS.getName()));
        locationContext.setName(parentMenuName);

        Menu fileMenu = makeFileMenuIfNotExisting(parentMenuName, locationContext);

        Menu recentMenu = new Menu(menuName);
        new MenuOperator(fileMenu).insert(recentMenu).after("Open");

        ObservableList<MenuItem> items = recentMenu.getItems();
        items.addListener((ListChangeListener<MenuItem>) c -> {
            if (c.next()) {
                if (c.wasAdded()) {
                    items.sort(Comparator.comparing(MenuItem::getText));
                    if (logger.isDebugEnabled()) {
                        String itemCSV = items.stream().map(item -> item.getText()).collect(Collectors.joining(", "));
                        logger.debug("Sorted {} menu with items: {}", menuName, itemCSV);
                    }
                }
            }
        });
        if (logger.isDebugEnabled()) {
            String itemCSV = items.stream().map(item -> item.getText()).collect(Collectors.joining(", "));
            logger.debug("Initial {} menu with items: {}", menuName, itemCSV);
        }


        recentFiles.addListener((ListChangeListener<FileOptions>) c -> {
            if (c.next()) {
                if (c.wasAdded()) {
                    for (FileOptions added : c.getAddedSubList()) {
                        MenuItem menuItem = null;
                        try {
                            menuItem = new OpenRecentFileFactory(added).makeNode(locationContext);
                        } catch (CoreException e) {
                            logger.error("Unable to add menu item for recent file [" + added.getFileName() + "]: ", e);
                        }
                        items.add(menuItem);
                    }
                    items.sort(Comparator.comparing(MenuItem::getText));
                }
            }
        });
        return recentFiles.stream().map(option -> new OpenRecentFileFactory(option)).collect(Collectors.toList());
    }
}
