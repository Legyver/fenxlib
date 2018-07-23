package com.legyver.fenxlib.uimodel;

import java.util.List;

public interface RecentFileAware extends WorkingFileAware {

	List<FileOptions> getRecentFiles();
}
