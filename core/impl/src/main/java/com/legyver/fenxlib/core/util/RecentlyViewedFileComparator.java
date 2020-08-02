package com.legyver.fenxlib.core.util;

import com.legyver.fenxlib.core.config.parts.RecentlyViewedFile;
import java.util.Comparator;

public class RecentlyViewedFileComparator implements Comparator<RecentlyViewedFile> {

	@Override
	public int compare(RecentlyViewedFile o1, RecentlyViewedFile o2) {
		return o1.getLastAccessed().compareTo(o2.getLastAccessed());
	}

}
