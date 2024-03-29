package com.legyver.fenxlib.core.menu.options;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.api.config.IApplicationConfig;
import com.legyver.fenxlib.api.config.parts.RecentFile;
import com.legyver.fenxlib.api.config.parts.RecentFiles;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.DefaultFileOptions;
import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.locator.LocationContext;
import com.legyver.fenxlib.api.controls.ControlsFactory;
import com.legyver.fenxlib.core.config.ICoreApplicationConfig;
import com.legyver.fenxlib.core.config.CoreConfigSection;
import com.legyver.fenxlib.core.menu.templates.file.OpenRecentFileFactory;
import com.legyver.fenxlib.api.scene.controls.options.MenuItemOptions;
import com.legyver.fenxlib.api.scene.controls.options.MenuOptions;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

/**
 * Menu option to display a recent files section to facilitate opening recent files
 */
public class RecentFilesMenuProducer extends AbstractMenuItemProducer {
    private static final Logger logger = LogManager.getLogger(RecentFilesMenuProducer.class);

    /**
     * Construct a menu option to display a recent files section to facilitate opening recent files
     * @param text the text for the menu option
     */
    public RecentFilesMenuProducer(String text) {
        super(text, null);
    }

    /**
     * Construct a menu option to display a recent files section to facilitate opening recent files using the default text
     * specified by the "legyver.defaults.label.menu.file.recent" property
     */
    public RecentFilesMenuProducer() {
        this("legyver.defaults.label.menu.file.recent");
    }

    /**
     * Make the menu item to display recent files
     * @param locationContext the location context for the menu item
     * @return the produced menu item
     * @throws CoreException if there is an error during construction of components
     */
    public MenuItem makeMenuItem(LocationContext locationContext) throws CoreException {
        Menu recentMenuItem = ControlsFactory.make(Menu.class, locationContext, new MenuOptions().text(text).useTextForName(true));
        ObservableList<MenuItem> items = recentMenuItem.getItems();

        List<FileOptions> recentFileList = getRecentFiles();
        recentFileList.sort(Comparator.comparing(FileOptions::getFileName));
        ObservableList<FileOptions> recentFiles = FXCollections.observableArrayList(recentFileList);
        recentFiles.addListener((ListChangeListener<FileOptions>) c -> {
            if (c.next()) {
                if (c.wasAdded()) {
                    for (FileOptions added : c.getAddedSubList()) {
                        MenuItem menuItem = null;
                        try {
                            menuItem = new OpenRecentFileFactory(added).makeNode(locationContext, new MenuItemOptions());
                        } catch (CoreException e) {
                            logger.error("Unable to add menu item for recent file [" + added.getFileName() + "]: ", e);
                        }
                        items.add(menuItem);
                    }
                    items.sort(Comparator.comparing(MenuItem::getText));
                }
            }
        });
        return recentMenuItem;
    }

    private List<FileOptions> getRecentFiles() throws CoreException {
        List<FileOptions> recentFileList = new ArrayList<>();
        IApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
        if (applicationConfig instanceof ICoreApplicationConfig) {
            CoreConfigSection configSection = ((ICoreApplicationConfig) applicationConfig).getCoreConfig();
            if (configSection == null) {
                configSection = new CoreConfigSection();
                ((ICoreApplicationConfig) applicationConfig).setCoreConfig(configSection);
            }
            RecentFiles recentFiles = configSection.getRecentFiles();
            List<RecentFile> recentlyViewedFiles = recentFiles.getValues();
            for (RecentFile recentlyViewedFile : recentlyViewedFiles) {
                FileOptions fileOptions = new DefaultFileOptions();
                fileOptions.setFileName(recentlyViewedFile.getName());
                fileOptions.setFilePath(recentlyViewedFile.getPath());
                recentFileList.add(fileOptions);
            }
        }
        return recentFileList;
    }

}
