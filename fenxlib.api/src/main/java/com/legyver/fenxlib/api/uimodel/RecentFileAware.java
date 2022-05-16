package com.legyver.fenxlib.api.uimodel;

import com.legyver.fenxlib.api.files.FileOptions;
import javafx.collections.ObservableList;

/**
 * A UI Model that displays a list of recent files opened
 */
public interface RecentFileAware extends IUiModel {

	/**
	 * Get a list of file options representing recently-opened files
	 * @return the list of recently-opened files
	 */
	ObservableList<FileOptions> getRecentFiles();
}
