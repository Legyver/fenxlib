package com.legyver.fenxlib.core.impl.util;

import com.legyver.fenxlib.core.api.files.FileOptions;
import com.legyver.fenxlib.core.api.uimodel.RecentFileAware;

import java.util.ArrayList;
import java.util.List;

public class TestUiModel implements RecentFileAware {
	List<FileOptions> recentFiles = new ArrayList<>();

	@Override
	public List<FileOptions> getRecentFiles() {
		return recentFiles;
	}

}
