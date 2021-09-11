package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyViewedFile;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyModified;
import com.legyver.fenxlib.core.api.event.correlation.CorrelatingEventHandlerFactory;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * Factory to create a Menu Item for a recently opened file
 */
public class RecentlyOpenedFileFactory implements IMenuItemFactory {
	private static final Logger logger = LogManager.getLogger(RecentlyOpenedFileFactory.class);

	private final String name;
	private final ThrowingConsumer<File> fileSelectionConsumer;
	private final List<IRecentlyViewedFile> recentlyViewedFiles;

	/**
	 * Construct a factory to create a RecentlyOpened menu item with a list of sub menu-items for each recently viewed file
	 * @param name the name of the menu item, ex: "Recent"
	 * @param recentlyViewedFiles the list of recently-viewed files to create menu items for
	 * @param fileSelectionConsumer any action to be taken when a particular recently opened file is selected
	 */
	public RecentlyOpenedFileFactory(String name, List<IRecentlyViewedFile> recentlyViewedFiles, ThrowingConsumer<File> fileSelectionConsumer) {
		this.name = name;
		this.recentlyViewedFiles = recentlyViewedFiles;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}

	/**
	 * Construct a factory to create a RecentlyOpened menu item with a list of sub menu-items for each recently viewed file
	 * @param name the name of the menu item, ex: "Recent"
	 * @param fileSelectionConsumer any action to be taken when a particular recently opened file is selected
	 * @throws CoreException if there is an error reading the list of recently opened files
	 */
	public RecentlyOpenedFileFactory(String name, ThrowingConsumer<File> fileSelectionConsumer) throws CoreException {
		this(name, getRecentlyViewedFiles(), fileSelectionConsumer);
	}

	private static List<IRecentlyViewedFile> getRecentlyViewedFiles() throws CoreException {
		IRecentlyModified recentlyModified = ApplicationContext.getApplicationConfig().getRecentlyModified();
		return recentlyModified.getValues();
	}
	
	@Override
	public MenuItem makeItem(LocationContext locationContext) throws CoreException {
		Menu menu = new Menu(name);
		menu.setDisable(true);
		if (recentlyViewedFiles != null) {
			CoreException.unwrap(() -> recentlyViewedFiles.stream()
					.map((recentFile) -> CoreException.wrap(
							() -> makeItem(recentFile))
					).forEach(item -> menu.getItems().add(item))
			);
			menu.setDisable(false);
		}
		return menu;
	}
	
	private MenuItem makeItem(IRecentlyViewedFile recentFile) throws CoreException {
		MenuItem recent = new MenuItem(recentFile.getName());
		recent.setOnAction(CorrelatingEventHandlerFactory.wrapIfNecessary(av -> {
			try {
				File file = new File(recentFile.getPath());//not validating file.exists() here so devs can handle themselves
				fileSelectionConsumer.accept(file);
			} catch (CoreException ex) {
				logger.error("Error reading recent file information: " + ex.getMessage(), ex);
			}
		}));
		return recent;
	}

}
