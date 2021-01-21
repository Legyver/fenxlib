package com.legyver.fenxlib.core.impl.util;

import com.legyver.fenxlib.core.impl.config.IRecentlyViewedFile;

import java.util.Comparator;

public class RecentlyViewedFileComparator implements Comparator<IRecentlyViewedFile> {

	@Override
	public int compare(IRecentlyViewedFile o1, IRecentlyViewedFile o2) {
		return o1.getLastAccessed().compareTo(o2.getLastAccessed());
	}

}
