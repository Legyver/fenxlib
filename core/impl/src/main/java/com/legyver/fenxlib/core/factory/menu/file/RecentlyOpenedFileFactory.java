package com.legyver.fenxlib.core.factory.menu.file;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.config.IRecentlyViewedFile;
import com.legyver.fenxlib.core.config.parts.ILastOpened;
import com.legyver.fenxlib.core.config.parts.IRecentlyModified;
import com.legyver.fenxlib.core.config.parts.RecentlyViewedFile;
import com.legyver.fenxlib.core.context.ApplicationContext;
import com.legyver.fenxlib.core.factory.menu.IMenuItemFactory;
import com.legyver.fenxlib.core.uimodel.FileOptions;
import java.io.File;
import java.util.List;
import java.util.function.Consumer;

import com.legyver.fenxlib.core.util.RecentlyViewedFileComparator;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class RecentlyOpenedFileFactory implements IMenuItemFactory<FileOptions> {

	private final String name;
	private final Consumer<File> fileSelectionConsumer;
	private final List<IRecentlyViewedFile> recentlyViewedFiles;

	public RecentlyOpenedFileFactory(String name, List<IRecentlyViewedFile> recentlyViewedFiles, Consumer<File> fileSelectionConsumer) {
		this.name = name;
		this.recentlyViewedFiles = recentlyViewedFiles;
		this.fileSelectionConsumer = fileSelectionConsumer;
	}

	public RecentlyOpenedFileFactory(String name, Consumer<File> fileSelectionConsumer) {
		this(name, getRecentlyViewedFiles(), fileSelectionConsumer);
	}

	private static List<IRecentlyViewedFile> getRecentlyViewedFiles() {
		IRecentlyModified recentlyModified = ApplicationContext.getApplicationConfig().getRecentlyModified();
		return recentlyModified.getValues();
	}
	
	@Override
	public MenuItem makeItem() throws CoreException {
		Menu menu = new Menu(name);
		menu.setDisable(true);
		if (recentlyViewedFiles != null) {
			recentlyViewedFiles.stream().map(this::makeItem).forEach(item -> menu.getItems().add(item));
			menu.setDisable(false);
		}
		return menu;
	}
	
	private MenuItem makeItem(IRecentlyViewedFile recentFile) {
		MenuItem recent = new MenuItem(recentFile.getName());
		recent.setOnAction(av -> {
			File file = new File(recentFile.getPath());//not validating file.exists() here so devs can handle themselves
			fileSelectionConsumer.accept(file);
		});
		return recent;
	}

}
