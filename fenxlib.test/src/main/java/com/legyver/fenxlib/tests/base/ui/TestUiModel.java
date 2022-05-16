package com.legyver.fenxlib.tests.base.ui;

import com.legyver.fenxlib.api.files.FileOptions;
import com.legyver.fenxlib.api.uimodel.RecentFileAware;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Test UI that "remembers" recent files
 */
public class TestUiModel implements RecentFileAware {
	private final ObservableList<FileOptions> recentFiles = FXCollections.observableArrayList();

	@Override
	public ObservableList<FileOptions> getRecentFiles() {
		return recentFiles;
	}

}
