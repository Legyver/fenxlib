package com.legyver.fenxlib.core.impl.factory.menu.file.internal;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.parts.ILastOpened;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.beans.property.ObjectProperty;

import java.io.File;

public class AbstractChooserFactory {
	protected final DefaultFileBrowseLocation defaultFileBrowseLocation;

	protected AbstractChooserFactory(DefaultFileBrowseLocation defaultFileBrowseLocation) {
		this.defaultFileBrowseLocation = defaultFileBrowseLocation;
	}

	protected AbstractChooserFactory() throws CoreException {
		this(getDefaultFileBrowseLocation());
	}

	protected static DefaultFileBrowseLocation getDefaultFileBrowseLocation() throws CoreException {
		DefaultFileBrowseLocation defaultFileBrowseLocation = new DefaultFileBrowseLocation();
		File lastOpenedFileLocation = getLastOpenedFileLocation();
		if (lastOpenedFileLocation != null && lastOpenedFileLocation.exists()) {
			defaultFileBrowseLocation.setInitialDirectory(lastOpenedFileLocation.getParentFile());
		}

		return defaultFileBrowseLocation;
	}

	private static File getLastOpenedFileLocation() throws CoreException {
		ILastOpened lastOpened = ApplicationContext.getApplicationConfig().getLastOpened();
		String lastFileName = lastOpened.getLastFile();
		File lastFile = null;
		if (lastFileName != null) {
			lastFile = new File(lastFileName);
		}
		return lastFile;
	}


	protected void bindDefaultBrowseLocation(ObjectProperty<File> initialDirectoryProperty) {
		ObjectProperty<File> initialDirectory = defaultFileBrowseLocation.initialDirectory();
		if (initialDirectory != null && initialDirectory.get() != null && initialDirectory.get().isDirectory()) {
			initialDirectoryProperty.bind(initialDirectory);
		}
	}
}
