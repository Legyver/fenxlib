package com.legyver.fenxlib.api.files;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maintain recently modified file lists
 */
public class FileRegistry {
	private final DefaultFileBrowseLocation defaultFileBrowseLocation = new DefaultFileBrowseLocation();
	private final ObservableList<FileOptions> openFiles = FXCollections.observableArrayList();
	private final Map<String, FileOptions> openFileConfigMap = new HashMap<>();
	private final ObjectProperty<FileOptions> active = new SimpleObjectProperty<>();

	/**
	 * Construct an registry of open files.
	 * Any time a file is opened
	 * - the open files list is updated
	 * - the default browse location is updated so all file/directory chooser browse windows update to the last-opened location
	 */
	public FileRegistry() {
		openFiles.addListener((ListChangeListener<FileOptions>) change -> {
			if (change.next()) {
				if (change.wasAdded()) {
					List<? extends FileOptions> added = change.getAddedSubList();
					for (int i = 0; i < added.size(); i++) {
						FileOptions opened = added.get(i);
						defaultFileBrowseLocation.setInitialDirectory(opened.getFile().getParentFile());
						openFileConfigMap.put(opened.getFilePath(), opened);
						active.set(opened);
					}
				} else if (change.wasRemoved()) {
					//avoid a memory leak
					List<? extends FileOptions> removed = change.getRemoved();
					FileOptions open = active.get();
					for (int i = 0; i < removed.size(); i++) {
						FileOptions closed = removed.get(i);
						openFileConfigMap.remove(closed.getFilePath());
						if (open.getFilePath().equals(closed.getFilePath())) {
							active.set(null);
						}
					}
				}
			}
		});
	}

	/**
	 * Get observable list of open files
	 * @return list of open files
	 */
	public ObservableList<FileOptions> getOpenFiles() {
		return openFiles;
	}

	/**
	 * Get active file.
	 * If no files are open it will return null;
	 * @return the active file
	 */
	public FileOptions getActive() {
		return active.get();
	}

	/**
	 * Get the FileOptions based on the absolute path of the file.
	 * @param absolutePath the absolute path to the file.
	 * @return the appropriate file options or null
	 */
	public FileOptions lookup(String absolutePath) {
		return openFileConfigMap.get(absolutePath);
	}

	/**
	 * Get the default file browse location.
	 * While the DefaultFileBrowseLocation is guaranteed to be not-null and the same can be said for
	 * both its initialDirectory and initialFileName properties, the values contained by these properties may be null.
	 * @return the default file browse location
	 */
	public DefaultFileBrowseLocation getDefaultFileBrowseLocation() {
		return defaultFileBrowseLocation;
	}
}
