package com.legyver.fenxlib.core.impl.uimodel;

import com.legyver.fenxlib.core.api.uimodel.IUiModel;

import java.util.List;

/**
 * A UI Model that displays a list of recent files opened
 */
public interface RecentFileAware extends IUiModel {

	/**
	 * Get a list of file options representing recently-opened files
	 * @return the list of recently-opened files
	 */
	List<FileOptions> getRecentFiles();
}
