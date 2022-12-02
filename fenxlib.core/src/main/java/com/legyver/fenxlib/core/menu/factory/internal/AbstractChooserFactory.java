package com.legyver.fenxlib.core.menu.factory.internal;

import com.legyver.fenxlib.api.config.IApplicationConfig;
import com.legyver.fenxlib.api.config.parts.LastOpened;
import com.legyver.fenxlib.api.context.ApplicationContext;
import com.legyver.fenxlib.api.files.DefaultFileBrowseLocation;
import com.legyver.fenxlib.core.config.CoreConfigSection;
import com.legyver.fenxlib.core.config.ICoreApplicationConfig;
import javafx.beans.property.ObjectProperty;

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
	 */
	protected AbstractChooserFactory() {
		this(getDefaultFileBrowseLocation());
	}

	/**
	 * Get the default browse default file browse location as read from the config file.
	 * @return If the config file does not specify a last-opened location the location is not set which results in using the Java file/directory chooser default location.
	 */
	protected static DefaultFileBrowseLocation getDefaultFileBrowseLocation() {
		DefaultFileBrowseLocation defaultFileBrowseLocation = new DefaultFileBrowseLocation();
		File lastOpenedFileLocation = getLastOpenedFileLocation();
		if (lastOpenedFileLocation != null && lastOpenedFileLocation.exists()) {
			defaultFileBrowseLocation.setInitialDirectory(lastOpenedFileLocation.getParentFile());
		}

		return defaultFileBrowseLocation;
	}

	private static File getLastOpenedFileLocation() {
		File lastFile = null;

		IApplicationConfig applicationConfig = ApplicationContext.getApplicationConfig();
		if (applicationConfig instanceof ICoreApplicationConfig) {
			CoreConfigSection configSection = ((ICoreApplicationConfig) applicationConfig).getCoreConfig();
			if (configSection == null) {
				configSection = new CoreConfigSection();
				((ICoreApplicationConfig) applicationConfig).setCoreConfig(configSection);
			}
			LastOpened lastOpened = configSection.getLastOpened();
			String lastFileName = lastOpened.getFilePath();

			if (lastFileName != null) {
				lastFile = new File(lastFileName);
			}
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
