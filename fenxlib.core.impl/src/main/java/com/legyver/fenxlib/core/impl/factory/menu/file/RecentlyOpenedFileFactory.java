package com.legyver.fenxlib.core.impl.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.core.function.ThrowingConsumer;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyViewedFile;
import com.legyver.fenxlib.core.api.config.parts.IRecentlyModified;
import com.legyver.fenxlib.core.api.locator.LocationContext;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import com.legyver.fenxlib.core.impl.factory.menu.IMenuItemFactory;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

public class RecentlyOpenedFileFactory implements IMenuItemFactory {
	private static final Logger logger = LogManager.getLogger(RecentlyOpenedFileFactory.class);

	private final String name;
	private final ThrowingConsumer<File> fileSelectionConsumer;
	private final List<IRecentlyViewedFile> recentlyViewedFiles;

	public RecentlyOpenedFileFactory(String name, List<IRecentlyViewedFile> recentlyViewedFiles, ThrowingConsumer<File> fileSelectionConsumer) {
		this.name = name;
		this.recentlyViewedFiles = recentlyViewedFiles;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}

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
		recent.setOnAction(av -> {
			try {
				File file = new File(recentFile.getPath());//not validating file.exists() here so devs can handle themselves
				fileSelectionConsumer.accept(file);
			} catch (CoreException ex) {
				logger.error("Error reading recent file information: " + ex.getMessage(), ex);
			}
		});
		return recent;
	}

}
