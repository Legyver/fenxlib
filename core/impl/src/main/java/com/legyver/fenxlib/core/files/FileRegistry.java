package com.legyver.fenxlib.core.files;

import com.legyver.fenxlib.core.factory.menu.file.DefaultFileBrowseLocation;
import com.legyver.fenxlib.core.uimodel.FileOptions;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileRegistry {
	private DefaultFileBrowseLocation defaultFileBrowseLocation = new DefaultFileBrowseLocation();
	private ObservableList<FileOptions> openFiles = FXCollections.observableArrayList();
	private Map<String, FileOptions> openFileConfigMap = new HashMap<>();

	public FileRegistry() {
		openFiles.addListener((ListChangeListener<FileOptions>) change -> {
			if (change.wasAdded()) {
				List<? extends FileOptions> added = change.getAddedSubList();
				for (int i = 0; i < added.size(); i++) {
					FileOptions opened = added.get(i);
					defaultFileBrowseLocation.setInitialDirectory(opened.getFile().getParentFile());
					openFileConfigMap.put(opened.getFilePath(), opened);
				}
			} else if (change.wasRemoved()) {
				//avoid a memory leak
				List<? extends FileOptions> removed = change.getRemoved();
				for (int i = 0; i < removed.size(); i++) {
					FileOptions closed = removed.get(i);
					openFileConfigMap.remove(closed.getFilePath());
				}
			}
		});
	}

	public ObservableList<FileOptions> getOpenFiles() {
		return openFiles;
	}

	public FileOptions lookup(String absolutePath) {
		return openFileConfigMap.get(absolutePath);
	}

	public DefaultFileBrowseLocation getDefaultFileBrowseLocation() {
		return defaultFileBrowseLocation;
	}
}