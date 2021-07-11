package com.legyver.fenxlib.core.impl.factory.menu.file.internal;

import com.legyver.core.exception.CoreException;
import com.legyver.fenxlib.core.api.config.parts.ILastOpened;
import com.legyver.fenxlib.core.impl.context.ApplicationContext;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.MenuItem;

import java.io.File;

/**
 * Base class for File/Directory chooser factories.
 * Serves the purpose of having the File/Directory chooser open by default in the last used location.
 */
public class AbstractChooserFactory {
	/**
	 * The default filesystem location to open the file/directory browser
	 */
	protected final DefaultFileBrowseLocation defaultFileBrowseLocation;

	/**
	 * Construct an AbstractChooserFactory that makes file/directory choosers based on the default browse location
	 * @param defaultFileBrowseLocation the default filesystem location that file/directory choosers open to
	 */
	protected AbstractChooserFactory(DefaultFileBrowseLocation defaultFileBrowseLocation) {
		this.defaultFileBrowseLocation = defaultFileBrowseLocation;
	}

	/**
	 * Construct an AbstractChooserFactory using the default file browse location as read from the config file.
	 * If the config file does not specify a last-opened location the location is not set which results in using the Java file/directory chooser default location.
	 * @throws CoreException if an error is raised reading the last opened location from the config file.
	 */
	protected AbstractChooserFactory() throws CoreException {
		this(getDefaultFileBrowseLocation());
	}

	/**
	 * Get the default browse default file browse location as read from the config file.
	 * @return If the config file does not specify a last-opened location the location is not set which results in using the Java file/directory chooser default location.
	 * @throws CoreException if an error is raised reading the last opened location from the config file.
	 */
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

	/**
	 * Bind the default browse location of any file/directory chooser to the passed in property
	 * @param initialDirectoryProperty the passed in property to use for the initial browse location
	 */
	protected void bindDefaultBrowseLocation(ObjectProperty<File> initialDirectoryProperty) {
		ObjectProperty<File> initialDirectory = defaultFileBrowseLocation.initialDirectory();
		if (initialDirectory != null && initialDirectory.get() != null && initialDirectory.get().isDirectory()) {
			initialDirectoryProperty.bind(initialDirectory);
		}
	}
}
