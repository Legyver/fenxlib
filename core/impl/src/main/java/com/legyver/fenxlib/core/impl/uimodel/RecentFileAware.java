package com.legyver.fenxlib.core.impl.uimodel;

import com.legyver.fenxlib.core.api.uimodel.IUiModel;

import java.util.List;

public interface RecentFileAware extends IUiModel {

	List<FileOptions> getRecentFiles();
}
